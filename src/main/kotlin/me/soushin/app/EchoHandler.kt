package me.soushin.app

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.stream.Stream

class EchoHandler {
    fun echo(request: ServerRequest): Mono<ServerResponse> {
        val query = request.queryParam("q").let {
            if (it.isPresent) it.get()
            else "none"
        }
        return ServerResponse.ok()
            .syncBody("echo ${query}")
    }

    fun echoWithSse(request: ServerRequest): Mono<ServerResponse> {
        val query = request.queryParam("q").let {
            if (it.isPresent) it.get()
            else "none"
        }
        val interval = Flux.interval(Duration.ofSeconds(1))
        val stream = Flux.fromStream(Stream.generate { query } )
        return ServerResponse.ok()
            .bodyToServerSentEvents(Flux.zip(interval, stream).map { it.t2 })
    }
}
