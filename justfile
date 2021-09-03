findBook:
   rsc --request --route=findBook --data=book-1 --debug ws://localhost:8080/rsocket

findAuthor:
   rsc --request --route=findAuthor --data=author-1 --debug ws://localhost:8080/rsocket

graphql:
   cat graphql-query.json | rsc --request --route=graphql --data=- --debug ws://localhost:8080/rsocket