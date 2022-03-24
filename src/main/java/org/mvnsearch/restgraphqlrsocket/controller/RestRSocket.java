package org.mvnsearch.restgraphqlrsocket.controller;

import org.springframework.core.annotation.AliasFor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * Combined annotation for REST and RSocket
 *
 * @author linux_china
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@MessageMapping
@ResponseBody
public @interface RestRSocket {
    @AliasFor(annotation = RequestMapping.class, value = "value")
    String path() default "";

    @AliasFor(annotation = MessageMapping.class, value = "value")
    String route() default "";
}
