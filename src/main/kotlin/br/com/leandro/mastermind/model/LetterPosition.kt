package br.com.leandro.mastermind.model

data class LetterPosition(val state: LetterState)

enum class LetterState(val value: String) {
    RIGHT("right"), WRONG("wrong"), HALF("half")
}
