package com.sm.poke_domain.di

import com.sm.poke_data.di.pokeDataModule
import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_data.repository.PokeTypeRepository
import com.sm.poke_domain.use_cases.GetOutstandingPokemonListUseCase
import com.sm.poke_domain.use_cases.GetOutstandingPokemonListUseCaseImpl
import com.sm.poke_domain.use_cases.GetPokeDetailUseCase
import com.sm.poke_domain.use_cases.GetPokeDetailUseCaseImpl
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import com.sm.poke_domain.use_cases.GetPokeListUseCaseImpl
import com.sm.poke_domain.use_cases.GetPokeSearchByName
import com.sm.poke_domain.use_cases.GetPokeSearchByNameImpl
import com.sm.poke_domain.use_cases.GetPokeTypesUseCase
import com.sm.poke_domain.use_cases.GetPokeTypesUseCaseImpl
import org.koin.dsl.module

val pokeDomainModule = pokeDataModule.plus(
    module {
        factory {
            getRefListUseCase(get(), get())
        }
        factory {
            getPokeRefUseCase(get(), get())
        }
        factory {
            getPokeDetailUseCase(get())
        }
        factory {
            getOutstandingPokemonListUseCase(get(), get())
        }
        factory {
            getPokeTypesUseCase(get(), get())
        }
    }
)

fun getPokeTypesUseCase(repo: PokeTypeRepository, useCase: GetPokeDetailUseCase): GetPokeTypesUseCase {
    return GetPokeTypesUseCaseImpl(repo, useCase)
}

fun getOutstandingPokemonListUseCase(
    refRepo: PokeRefListRepository,
    detailRepo: PokeItemListRepository
): GetOutstandingPokemonListUseCase {
    return GetOutstandingPokemonListUseCaseImpl(refRepo, detailRepo)
}

private fun getPokeDetailUseCase(
    detailRepo: PokeItemListRepository
): GetPokeDetailUseCase {
    return GetPokeDetailUseCaseImpl(repository = detailRepo)
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