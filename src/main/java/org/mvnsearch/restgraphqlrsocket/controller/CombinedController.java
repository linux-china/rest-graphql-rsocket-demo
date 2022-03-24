package org.mvnsearch.restgraphqlrsocket.controller;

import org.mvnsearch.restgraphqlrsocket.domain.model.Author;
import org.mvnsearch.restgraphqlrsocket.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.ExecutionGraphQlRequest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.support.DefaultExecutionGraphQlRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Combined Controller with REST, GraphQL and RSocket
 *
 * @author linux_china
 */
@Controller
public class CombinedController {

    @RequestMapping("/book/{id}")
    @ResponseBody
    @MessageMapping("findBook")
    @QueryMapping("findBook")
    public Mono<Book> findBook(@PathVariable("id") @Argument String id) {
        return Mono.justOrEmpty(BOOKS.get(id));
    }

    @RestGraphqlRSocket(path = "/author/{id}", route = "findAuthor")
    @QueryMapping("findAuthor")
    public Mono<Author> findAuthor(@PathVariable("id") @Argument String id) {
        return Mono.justOrEmpty(AUTHORS.get(id));
    }

    @MessageMapping("graphql")
    public Mono<Map<String, Object>> graphql(Map<String, Object> input) {
        return graphQlService.execute(convertToRequestInput(input))
                .map(graphQlResponse -> graphQlResponse.getExecutionResult().toSpecification());
    }

    @SchemaMapping(typeName = "Book", field = "author")
    public Mono<Author> authorForBook(Book book) {
        return findAuthor(book.getAuthorId());
    }


    @Autowired
    private ExecutionGraphQlService graphQlService;
    public static Map<String, Book> BOOKS = Stream.of(
            new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
            new Book("book-2", "Moby Dick", 221, "author-2"),
            new Book("book-3", "Interview with the vampire", 224, "author-3")
    ).collect(Collectors.toMap(Book::getId, Function.identity()));

    public static Map<String, Author> AUTHORS = Stream.of(
            new Author("author-1", "firstname", "lastName"),
            new Author("author-2", "firstname", "lastName"),
            new Author("author-3", "firstname", "lastName")
    ).collect(Collectors.toMap(Author::getId, Function.identity()));


    private ExecutionGraphQlRequest convertToRequestInput(Map<String, Object> request) {
        String query = (String) request.get("query");
        String operationName = (String) request.get("operationName");
        @SuppressWarnings("unchecked")
        Map<String, Object> variables = (Map<String, Object>) request.get("variables");
        return new DefaultExecutionGraphQlRequest(query, operationName, variables, UUID.randomUUID().toString(), Locale.getDefault());
    }
}
