package br.com.leandro.mastermind.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DatabaseConfig {

    private val connectionDatabaseUrl = System.getenv("DATABASE_CONNECTION_URL")

    private val config = HikariConfig().apply {
        jdbcUrl = connectionDatabaseUrl
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = 5
    }

    private val dataSource = HikariDataSource(config)

    fun connection(): Database {
        return Database.connect(dataSource)
    }

}