package mastermind

import java.util.*

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secretWord: Word, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0

    val guessWord = guess.uppercase(Locale.getDefault())
    val filterWord = secretWord.normalize()
    val word = secretWord.word

    val result = arrayOfNulls<String>(filterWord.length)
    val secretMissing = mutableListOf<Char>()
    val wrongGuess = arrayOfNulls<Char>(filterWord.length)

    val charSecretList = filterWord.uppercase(Locale.getDefault()).toList()

    guessWord.toList().forEachIndexed { index, guessChar ->
        if (charSecretList[index] == guessChar) {
            result[index] = word[index].uppercaseChar().green()
            rightPosition++
        } else {
            secretMissing.add(charSecretList[index])
            wrongGuess[index] = guessChar
        }
    }

    wrongGuess.forEachIndexed { index, char ->
        if (char != null) {
            val wrongIndex = secretMissing.indexOf(char)
            if (wrongIndex > -1) {
                result[index] = char.uppercaseChar().yellow()
                secretMissing.removeAt(wrongIndex)
                wrongPosition++
            } else {
                result[index] = char.uppercaseChar().toString()
            }
        }
    }

    println(result.joinToString(" ", "> ", " <"))

    return Evaluation(
        rightPosition = rightPosition,
        wrongPosition = wrongPosition
    )
}

fun Char.green(): String = "\u001b[32m$this\u001b[0m"
fun Char.yellow(): String = "\u001b[33m$this\u001b[0m"