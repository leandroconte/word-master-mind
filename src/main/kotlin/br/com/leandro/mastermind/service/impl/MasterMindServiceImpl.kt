package br.com.leandro.mastermind.service.impl

import br.com.leandro.mastermind.config.di
import br.com.leandro.mastermind.model.*
import br.com.leandro.mastermind.service.MasterMindService
import br.com.leandro.mastermind.service.WordService
import org.kodein.di.instance
import java.util.*

class MasterMindServiceImpl : MasterMindService {

    private val wordService: WordService by di.instance()

    override fun play(guessWord: GuessWord): GuessResponse {
        val secret = WordDTO(wordService.getOrCreateDailyWord())
        return evaluateGuess(secret, guessWord.word)
    }

    fun evaluateGuess(secretWord: WordDTO, guess: String): GuessResponse {
        val lettersPosition = mutableMapOf<Int, LetterPosition>()

        val guessWord = guess.uppercase(Locale.getDefault())
        val filterWord = secretWord.normalize()

//        val result = arrayOfNulls<String>(filterWord.length)
        val secretMissing = mutableListOf<Char>()
        val wrongGuess = arrayOfNulls<Char>(filterWord.length)

        val charSecretList = filterWord.uppercase(Locale.getDefault()).toList()

        guessWord.toList().forEachIndexed { index, guessChar ->
            if (charSecretList[index] == guessChar) {
//                result[index] = word[index].uppercaseChar().green()
                lettersPosition[index] = LetterPosition(LetterState.RIGHT)
            } else {
                secretMissing.add(charSecretList[index])
                wrongGuess[index] = guessChar
            }
        }

        wrongGuess.forEachIndexed { index, char ->
            if (char != null) {
                val wrongIndex = secretMissing.indexOf(char)
                if (wrongIndex > -1) {
//                    result[index] = char.uppercaseChar().yellow()
                    lettersPosition[index] = LetterPosition(LetterState.HALF)
                    secretMissing.removeAt(wrongIndex)
                } else {
                    lettersPosition[index] = LetterPosition(LetterState.WRONG)
//                    result[index] = char.uppercaseChar().toString()
                }
            }
        }

//        println(result.joinToString(" ", "> ", " <"))

        return GuessResponse(lettersPosition)
    }

}

fun Char.green(): String = "\u001b[32m$this\u001b[0m"
fun Char.yellow(): String = "\u001b[33m$this\u001b[0m"
