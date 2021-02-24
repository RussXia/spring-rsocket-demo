package com.example.rsocket.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xiazhengyue
 * @since 2021-02-24
 */
@Slf4j
@Controller
public class ServerController {

    @MessageMapping("/request-response")
    public Mono<String> requestResponse(String request) {
        log.info("receive request:{}", request);
        return Mono.just("Hello " + request);
    }

    @MessageMapping("/fire-and-forget")
    public Mono<Void> fireAndForget(String request) {
        log.info("receive request:{}", request);
        return Mono.empty();
    }

    @MessageMapping("/request-stream")
    public Flux<String> requestStream(String request) {
        log.info("receive request:{}", request);
        return Flux.just("hello", request, "welcome");
    }

    @MessageMapping("/request-channel")
    Flux<String> requestChannel(Flux<String> request) {
        return request
                .doOnNext(record -> log.info("record is {}.", record))
                .map(record -> "response from server to " + record)
                .log();
    }

    @MessageExceptionHandler
    public Mono<String> handleException(Exception e) {
        return Mono.just(e.getMessage());
    }

}
