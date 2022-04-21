REST-GraphQL-RSocket combo demo
===============================

Spring Boot app with HTTP REST API, GraphQL and RSocket.

# Features

* HTTP REST API

```http request
GET http://localhost:8080/book/book-1
```

* RSocket Request

```http request
### RSocket request
RSOCKET findBook
Host: ws://localhost:8080/rsocket
Content-Type: application/json

"book-1"
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
* Spring Boot GraphQL: https://docs.spring.io/spring-graphql/docs/1.0.0-RC1/reference/html/
* RSocket: https://rsocket.io/
* RSocket JetBrains Plugin: https://plugins.jetbrains.com/plugin/18195-rsocket-requests-in-http-client
* httpx JetBrains Plugin: https://plugins.jetbrains.com/plugin/18807-httpx-requests
