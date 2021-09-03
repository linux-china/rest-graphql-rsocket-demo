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

* HTTP REST

```http request
GET http://localhost:8080/book/book-1
```

* GraphQL

```http request
POST http://localhost:8080/graphql
Content-Type: application/json

< ./graphql-query.json
```

* RSocket

```bash
rsc --request --route=findBook --data=book-1 ws://localhost:8080/rsocket
```

* GraphQL over RSocket

```bash
cat graphql-query.json | rsc --request --route=graphql --data=- ws://localhost:8080/rsocket
```

# References

* Spring GraphQL: https://spring.io/projects/spring-graphql
* RSocket: https://rsocket.io/
