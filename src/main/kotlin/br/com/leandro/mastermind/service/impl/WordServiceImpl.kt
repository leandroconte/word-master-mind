package br.com.leandro.mastermind.service.impl

import br.com.leandro.mastermind.config.di
import br.com.leandro.mastermind.exception.AppBadRequestException
import br.com.leandro.mastermind.repository.DailyWordRepository
import br.com.leandro.mastermind.repository.WordRepository
import br.com.leandro.mastermind.service.WordService
import org.kodein.di.instance

class WordServiceImpl : WordService {

    private val dailyWordRepository: DailyWordRepository by di.instance()
    private val wordRepository: WordRepository by di.instance()

    override fun getOrCreateDailyWord(): String {
        return dailyWordRepository.findOfToday()?.word ?: createNewWord()
    }

    override fun exists(word: String): Boolean  = wordRepository.exists(word)

    override fun findOfToday(): String? {
        return dailyWordRepository.findOfToday()?.word
    }

    private fun createNewWord(): String {
        val newWord = wordRepository.getRandom() ?: throw AppBadRequestException("Não existe nenhuma palavra na tabela de words")
        dailyWordRepository.save(newWord)
        return newWord.word
    }

}