REST-GraphQL-RSocket combo demo
===============================

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

* HTTP REST API

```http request
GET http://localhost:8080/book/book-1
```

* GraphQL over HTTP

```http request
POST http://localhost:8080/graphql
Content-Type: application/json

< ./graphql-query.json
```

* RSocket over WebSocket

```http request
RSOCKET findBook
Host: ws://localhost:8080/rsocket
Content-Type: application/json

"book-1"
```

* GraphQL over RSocket

```http request
### GraphQL over RSocket
RSOCKET graphql
Host: ws://localhost:8080/rsocket
Content-Type: application/json

{
  "query": "{ findBook(id: \"book-1\") { id name } }"
}
```

# References

* Spring GraphQL: https://spring.io/projects/spring-graphql
* Spring Boot GraphQL: https://docs.spring.io/spring-boot/docs/2.7.0-M1/reference/html/web.html#web.graphql
* RSocket: https://rsocket.io/
* RSocket Requests In HTTP Client: https://plugins.jetbrains.com/plugin/18195-rsocket-requests-in-http-client
