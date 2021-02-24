package com.example.rsocket.client.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author xiazhengyue
 * @since 2021-02-24
 */
@Slf4j
@ShellComponent
public class ClientShell {

    @Autowired
    private RSocketRequester rSocketRequester;

    @ShellMethod("request channel")
    public void channel() {
        log.info("start request channel to server");

        Mono<String> setting1 = Mono.just("Hello1");
        Mono<String> setting2 = Mono.just("Hello2").delayElement(Duration.ofSeconds(5));
        Mono<String> setting3 = Mono.just("Hello3").delayElement(Duration.ofSeconds(15));

        Flux<String> settings = Flux.concat(setting1, setting2, setting3)
                .doOnNext(d -> log.info("\nSending setting for a {}-second interval.\n", d));

        this.rSocketRequester
                .route("/request-channel")
                .data(settings)
                .retrieveFlux(String.class)
                .subscribe(message -> log.info("Received: {}", message));
    }
}
