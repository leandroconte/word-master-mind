package br.com.leandro.mastermind.repository

import br.com.leandro.mastermind.model.Word
import br.com.leandro.mastermind.model.WordDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.count
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.ThreadLocalRandom

class WordRepository {

    private fun countTotal(): Long {
        var total = 0L
        transaction {
            val wordCount = Word.word.count()
            total = Word.slice(wordCount)
                .selectAll()
                .map { it[wordCount] }
                .first()
        }

        return total
    }

    fun exists(word: String): Boolean {
        var exists = false
        transaction {
            exists = Word
                .select { Word.word eq word }
                .map { toDTO(it) }
                .isNotEmpty()
        }

        return exists
    }

    fun getRandom(): WordDTO? {
        val total = countTotal()
        var word: WordDTO? = null
        transaction {
            word = Word.selectAll()
                .limit(1, ThreadLocalRandom.current().nextLong(total) - 1)
                .map { toDTO(it) }
                .firstOrNull()
        }

        return word
    }

    private fun toDTO(it: ResultRow): WordDTO = WordDTO(it[Word.word])

}
