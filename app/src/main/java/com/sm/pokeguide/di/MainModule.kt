package com.sm.pokeguide.di

import com.sm.core.navigation.di.navigationModule
import com.sm.core.network.di.networkCoreModule
import com.sm.core.ui.di.pokeCoreUiModule
import com.sm.poke_data.di.pokeDataModule
import com.sm.poke_domain.di.pokeDomainModule
import com.sm.poke_features.listing.di.pokeListingFeatModule
import com.sm.poke_features.search.di.searchFeatureModule
import org.koin.dsl.module

val mainModule = module {}
    .plus(networkCoreModule)
    .asSequence()
    .plus(navigationModule)
    .plus(pokeDataModule)
    .plus(pokeDomainModule)
    .plus(pokeListingFeatModule)
    .plus(searchFeatureModule)
    .plus(pokeCoreUiModule)
    .toList()