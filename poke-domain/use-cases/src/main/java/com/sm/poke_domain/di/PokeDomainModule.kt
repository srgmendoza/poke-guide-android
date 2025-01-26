package com.sm.poke_domain.di

import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_data.repository.PokeTypeRepository
import com.sm.poke_domain.use_cases.GetPokeSearchByName
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import com.sm.poke_domain.use_cases.GetPokeListUseCaseImpl
import com.sm.poke_domain.use_cases.GetPokeSearchByNameImpl
import org.koin.dsl.module

val pokeDomainModule = module {
    factory {
        getRefListUseCase(get(), get())
    }
    factory {
        getPokeRefUseCase(get(), get())
    }
}

private fun getRefListUseCase(
    refRepo: PokeRefListRepository,
    detailRepo: PokeItemListRepository
): GetPokeListUseCase {
    return GetPokeListUseCaseImpl(refRepo = refRepo, detailRepo = detailRepo)
}

private fun getPokeRefUseCase(
    detailRepo: PokeItemListRepository,
    typeRepo: PokeTypeRepository
): GetPokeSearchByName {
    return GetPokeSearchByNameImpl(detailRepo, typeRepo)
}