REST-GraphQL-RSocket demo
=========================

One Controller to combine HTTP REST, GraphQL and RSocket.

```java
@Controller
public class CombinedController {

    @RequestMapping("/book/{id}")
    @ResponseBody
    @MessageMapping("findBook")
    @QueryMapping("findBook")
    public Mono<Book> findBook(@PathVariable("id") @Argument String id) {
        return Mono.justOrEmpty(BOOKS.get(id));
    }

    @RestGraphqlRSocket(path = "/author/{id}", query = "findAuthor", route = "findAuthor")
    public Mono<Author> findAuthor(@PathVariable("id") @Argument String id) {
        return Mono.justOrEmpty(AUTHORS.get(id));
    }

    @SchemaMapping(typeName = "Book", field = "author")
    public Mono<Author> authorForBook(Book book) {
        return findAuthor(book.getAuthorId());
    }
}
```

# Features

* HTTP REST
* GraphQL
* RSocket
* GraphQL over RSocket

# References

* Spring GraphQL: https://spring.io/projects/spring-graphql
* RSocket: https://rsocket.io/
