package com.sm.pokeguide.app_tv.di

import com.sm.core.navigation.di.navigationModule
import com.sm.poke_tv_features.listing.di.pokeHomeTvFeatModule
import com.sm.poke_tv_features.search.di.pokeTvSearchFeatModule
import org.koin.dsl.module

val mainTvModule =  module {}
    .plus(navigationModule)
    .plus(pokeHomeTvFeatModule)
    .plus(pokeTvSearchFeatModule)