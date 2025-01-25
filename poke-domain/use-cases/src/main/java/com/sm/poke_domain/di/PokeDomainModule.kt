package com.sm.poke_domain.di

import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import com.sm.poke_domain.use_cases.GetPokeListUseCaseImpl
import org.koin.dsl.module

val pokeDomainModule = module {
    factory {
        getUseCase(get(), get())
    }
}

private fun getUseCase(
    refRepo: PokeRefListRepository,
    detailRepo: PokeItemListRepository
): GetPokeListUseCase {
    return GetPokeListUseCaseImpl(refRepo = refRepo, detailRepo = detailRepo)
}