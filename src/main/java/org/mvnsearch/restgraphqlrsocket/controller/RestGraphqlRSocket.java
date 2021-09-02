package org.mvnsearch.restgraphqlrsocket.controller;

import org.springframework.core.annotation.AliasFor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * Combined annotation for REST, GraphQL and RSocket
 *
 * @author linux_china
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@MessageMapping
@QueryMapping
@ResponseBody
public @interface RestGraphqlRSocket {
    @AliasFor(annotation = RequestMapping.class, value = "value")
    String path() default "";

    @AliasFor(annotation = QueryMapping.class, value = "value")
    String query() default "";

    @AliasFor(annotation = MessageMapping.class, value = "value")
    String route() default "";
}
