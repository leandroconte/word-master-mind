package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secretWord: Word, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0

    val guessWord = guess.toUpperCase()
    val filterWord = secretWord.normalize()
    val word = secretWord.word

    val result = arrayOfNulls<String>(filterWord.length)

    val secretMissing = mutableListOf<Char>()
    val wrongGuess = arrayOfNulls<Char>(filterWord.length)

    val charSecretList = filterWord.toUpperCase().toList()

//    val zip = secret.zip(guess);
//    val rightPositions = zip.count { pair ->
//        pair.first == pair.second
//    }
//
//    val commonLetters = "ABCDEF".sumBy { ch ->
//        Math.min(secret.count { c -> c == ch }, guess.count { c -> c == ch })
//    }

    guessWord.toList().forEachIndexed { index, guessChar ->
        if (charSecretList[index] == guessChar) {
            result[index] = word[index].toUpperCase().green()
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
                result[index] = char.toUpperCase().yellow()
                secretMissing.removeAt(wrongIndex)
                wrongPosition++
            } else {
                result[index] = char.toUpperCase().toString()
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