package com.sm.poke_domain.di

import com.sm.poke_data.repository.PokeNetworkRepository
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import com.sm.poke_domain.use_cases.GetPokeListUseCaseImpl
import org.koin.dsl.module

val pokeDomainModule = module {
    factory {
        getUseCase(get())
    }
}

private fun getUseCase(repo: PokeNetworkRepository): GetPokeListUseCase {
    return GetPokeListUseCaseImpl(repo = repo)
}