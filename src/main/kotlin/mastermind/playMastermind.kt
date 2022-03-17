package mastermind

import kotlin.random.Random

const val CODE_LENGTH = 5

fun main() {
    playMastermind()
}

fun playMastermind (secret: Word = generateSecret()) {
    var evaluation: Evaluation
    print("Welcome to Word Master Mind!\n" +
            "Try to figure out the 5 letter word or type 'answer' to reveal the word.\n")

    do {
        print("Your guess : ")
        var guess = readLine()!!

        if (askedForAnswer(guess)) {
            print("The answer is: ${secret.word}")
            break
        }

        while (validateInput(guess)) {
            println("Incorrect input: $guess. " +
                    "It should consist of $CODE_LENGTH letters " +
                    "Please try again.")
            guess = readLine()!!
        }

        evaluation = evaluateGuess(secret, guess)

        if (evaluation.isComplete()) {
            println("The guess is correct!")
        }

    } while (!evaluation.isComplete())
}

fun Evaluation.isComplete(): Boolean = rightPosition == CODE_LENGTH

fun validateInput(guess: String): Boolean {
    return guess.length != CODE_LENGTH
}

fun askedForAnswer(guess: String): Boolean {
    return "answer".equals(guess, ignoreCase = true)
}

fun generateSecret(): Word {
    return WORD_LIST.elementAt(Random.nextInt(WORD_LIST.size))
}
