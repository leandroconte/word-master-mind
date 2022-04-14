package br.com.leandro.mastermind.repository

import br.com.leandro.mastermind.model.DailyWord
import br.com.leandro.mastermind.model.DailyWordDTO
import br.com.leandro.mastermind.model.WordDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class DailyWordRepository {

    fun save(newWord: WordDTO) {
        transaction {
            DailyWord.insert {
                it[date] = LocalDate.now()
                it[word] = newWord.word
            }
        }
    }

    fun findOfToday(): DailyWordDTO? {
        var dailyWord: DailyWordDTO? = null

        transaction {
            dailyWord = DailyWord.select {
                DailyWord.date eq LocalDate.now()
            } .map { toDTO(it) }
                .firstOrNull()
        }

        return dailyWord
    }

    private fun toDTO(it: ResultRow) = DailyWordDTO(it[DailyWord.date], it[DailyWord.word])

}