package com.sm.pokeguide.di

import com.sm.core.navigation.di.navigationModule
import com.sm.core.network.di.networkCoreModule
import com.sm.poke_data.di.pokeDataModule
import com.sm.poke_domain.di.pokeDomainModule
import com.sm.poke_features.listing.di.pokeListingFeatModule
import org.koin.dsl.module

val mainModule = module {}
    .plus(networkCoreModule)
    .plus(navigationModule)
    .plus(pokeDataModule)
    .plus(pokeDomainModule)
    .plus(pokeListingFeatModule)