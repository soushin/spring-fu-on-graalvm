package me.soushin.app

import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.configuration
import org.springframework.fu.kofu.web.jackson
import org.springframework.fu.kofu.web.server
import org.springframework.web.reactive.function.server.router

fun routes(echoHandler: EchoHandler) = router {
    GET("/", echoHandler::echo)
    GET("/sse", echoHandler::echoWithSse)
}

val webConfig = configuration {
    beans {
        bean<EchoHandler>()
    }
    server {
        codecs {
            string()
            jackson()
        }
        import(::routes)
    }
}

val app = application {
    import(webConfig)
}

fun main(args: Array<String>) = app.run(args)
