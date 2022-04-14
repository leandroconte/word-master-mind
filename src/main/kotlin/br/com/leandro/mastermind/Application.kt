package br.com.leandro.mastermind

import br.com.leandro.mastermind.exception.AppBadRequestException
import br.com.leandro.mastermind.model.ApiError
import br.com.leandro.mastermind.routes.game
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

const val CODE_LENGTH = 5

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter())
        gson {
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }
    install(StatusPages) {
        exception<AppBadRequestException> { cause ->
            call.respond(HttpStatusCode.BadRequest, ApiError(cause.message?: HttpStatusCode.BadRequest.description))
        }
    }
    routing {
        game()
    }
}
