package br.com.leandro.mastermind.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

object DailyWord: Table("daily_word") {
    var date = date("date")
    var word = varchar("word", 5)
}

data class DailyWordDTO(val date: LocalDate, val word: String)
