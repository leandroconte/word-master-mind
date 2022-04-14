package br.com.leandro.mastermind.service

interface WordService {
    fun getOrCreateDailyWord(): String
    fun findOfToday(): String?
    fun exists(word: String): Boolean
}