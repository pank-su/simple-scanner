package su.pank.simplescanner.data.di

import org.koin.dsl.module
import su.pank.simplescanner.data.ScansRepository

val dataModule = module{
    single {
        ScansRepository(get())
    }
}