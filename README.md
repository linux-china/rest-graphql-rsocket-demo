REST-GraphQL-RSocket combo demo
===============================

Demo for HTTP REST, GraphQL and RSocket

# Features

* HTTP REST API

```http request
GET http://localhost:8080/book/book-1
```

* GraphQL over HTTP

```http request
### GraphQL over HTTP
GRAPHQL http://localhost:8080/graphql
Content-Type: application/graphql

query {
    findBook(id: "book-1") { id name }
}
```

* RSocket over WebSocket

```http request
### GraphQL over WebSocket
GRAPHQLWS localhost:8080/graphql
Content-Type: application/graphql

query {
    findBook(id: "book-1") { id name }
}
```

* GraphQL over RSocket

```http request
### GraphQL over RSocket
GRAPHQLRS graphql
Host: ws://localhost:8080/rsocket
Content-Type: application/graphql

query {
    findBook(id: "book-1") { id name }
}
```

# References

* Spring GraphQL: https://spring.io/projects/spring-graphql
* Spring Boot GraphQL: https://docs.spring.io/spring-boot/docs/2.7.0-M1/reference/html/web.html#web.graphql
* RSocket: https://rsocket.io/
* RSocket Requests In HTTP Client: https://plugins.jetbrains.com/plugin/18195-rsocket-requests-in-http-client
