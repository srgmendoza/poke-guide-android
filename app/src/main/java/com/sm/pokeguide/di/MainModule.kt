package com.sm.pokeguide.di

import com.sm.core.navigation.di.navigationModule
import com.sm.core.ui.di.pokeCoreUiModule
import com.sm.poke_data.di.pokeDataModule
import com.sm.poke_domain.di.pokeDomainModule
import com.sm.poke_features.detail.di.pokeDetailFeatModule
import com.sm.poke_features.listing.di.pokeListingFeatModule
import com.sm.poke_features.search.di.pokeSearchFeatModule
import org.koin.dsl.module

val mainModule = module {}
    .plus(navigationModule)
    .asSequence()
    .plus(pokeDataModule)
    .plus(pokeDomainModule)
    .plus(pokeListingFeatModule)
    .plus(pokeSearchFeatModule)
    .plus(pokeDetailFeatModule)
    .plus(pokeCoreUiModule)
    .toList()