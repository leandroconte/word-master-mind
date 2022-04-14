package br.com.leandro.mastermind.config

import br.com.leandro.mastermind.repository.DailyWordRepository
import br.com.leandro.mastermind.repository.WordRepository
import br.com.leandro.mastermind.service.MasterMindService
import br.com.leandro.mastermind.service.WordService
import br.com.leandro.mastermind.service.impl.MasterMindServiceImpl
import br.com.leandro.mastermind.service.impl.WordServiceImpl
import org.kodein.di.DI
import org.kodein.di.bindEagerSingleton
import org.kodein.di.bindSingleton

val di = DI {
    bindSingleton<MasterMindService> { MasterMindServiceImpl() }
    bindSingleton<WordService> { WordServiceImpl() }
    bindSingleton { DailyWordRepository() }
    bindSingleton { WordRepository() }
    bindEagerSingleton { DatabaseConfig().connection() }
}
