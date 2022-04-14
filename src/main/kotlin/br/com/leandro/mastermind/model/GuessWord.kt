package br.com.leandro.mastermind.model

data class GuessResponse(val letters: Map<Int, LetterPosition>)

data class GuessWord(val word: String)
