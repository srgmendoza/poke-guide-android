package com.sm.poke_data.di

import com.sm.core.network.NetworkCore
import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.repository.PokeNetworkRepository
import com.sm.poke_data.repository.PokeNetworkRepositoryImpl
import org.koin.dsl.module

val pokeDataModule = module {
    factory {
        getRepository(get())
    }
}

private fun getRepository(networkCore: NetworkCore): PokeNetworkRepository =
    PokeNetworkRepositoryImpl(getListingEndpoint(networkCore))

private fun getListingEndpoint(networkCore: NetworkCore): PokeApi =
    networkCore.getCoreNetwork(
        "https://pokeapi.co/api/v2/",
        PokeApi::class.java
    )