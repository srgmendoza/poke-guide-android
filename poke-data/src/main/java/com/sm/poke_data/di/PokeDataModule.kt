package com.sm.poke_data.di

import com.sm.core.network.NetworkCore
import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeItemListRepositoryImpl
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_data.repository.PokeRefListRepositoryImpl
import org.koin.dsl.module

val pokeDataModule = module {
    factory {
        getReferenceRepository(get())
    }
    factory {
        getDetailRepository(get())
    }
}

private fun getDetailRepository(networkCore: NetworkCore): PokeItemListRepository =
    PokeItemListRepositoryImpl(getPokeApi(networkCore))

private fun getReferenceRepository(networkCore: NetworkCore): PokeRefListRepository =
    PokeRefListRepositoryImpl(getPokeApi(networkCore))

private fun getPokeApi(networkCore: NetworkCore): PokeApi =
    networkCore.getCoreNetwork(
        "https://pokeapi.co/api/v2/",
        PokeApi::class.java
    )