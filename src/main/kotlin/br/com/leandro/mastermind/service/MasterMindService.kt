package br.com.leandro.mastermind.service

import br.com.leandro.mastermind.model.GuessResponse
import br.com.leandro.mastermind.model.GuessWord

interface MasterMindService {
    fun play(guessWord: GuessWord): GuessResponse
}