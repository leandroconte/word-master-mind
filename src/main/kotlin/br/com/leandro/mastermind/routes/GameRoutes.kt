package br.com.leandro.mastermind.routes

import br.com.leandro.mastermind.CODE_LENGTH
import br.com.leandro.mastermind.config.di
import br.com.leandro.mastermind.exception.AppBadRequestException
import br.com.leandro.mastermind.model.GuessWord
import br.com.leandro.mastermind.service.MasterMindService
import br.com.leandro.mastermind.service.WordService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance

val wordService: WordService by di.instance()
val masterMindService: MasterMindService by di.instance()

fun Route.game() {
    route("") {
        get("daily-word") {
            val dailyWord = wordService.findOfToday()
            call.respond("Palavra do dia: $dailyWord")
        }
        post {
            val guessWord = call.receive<GuessWord>()
            validateInput(guessWord)
            call.respond(masterMindService.play(guessWord))
        }
    }
}

private fun validateInput(guessWord: GuessWord) {
    if (guessWord.word.length != CODE_LENGTH) {
        throw AppBadRequestException("A palavra precisa conter apenas 5 letras")
    }

    if (!wordService.exists(guessWord.word)) {
        throw AppBadRequestException("A palavra não existe")
    }
}

