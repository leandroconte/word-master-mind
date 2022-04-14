package br.com.leandro.mastermind.model

import org.jetbrains.exposed.sql.Table

object Word: Table("word") {
    var word = varchar("word", 5)
}

data class WordDTO(val word: String)

fun WordDTO.normalize(): String {
    return this.word
        .map { char -> normalizeMap[char]?: char  }
        .joinToString("")
}

val normalizeMap = mapOf(
    'á' to 'a',
    'à' to 'a',
    'â' to 'a',
    'ã' to 'a',

    'é' to 'e',
    'è' to 'e',
    'ê' to 'e',
    'ẽ' to 'e',

    'í' to 'i',
    'ì' to 'i',
    'î' to 'i',
    'ĩ' to 'i',

    'ó' to 'o',
    'ò' to 'o',
    'ô' to 'o',
    'õ' to 'o',

    'ú' to 'u',
    'ù' to 'u',
    'û' to 'u',
    'ũ' to 'u',

    'ç' to 'c'
)
