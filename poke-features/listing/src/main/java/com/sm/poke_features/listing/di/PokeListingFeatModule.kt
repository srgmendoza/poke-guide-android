package com.sm.poke_features.listing.di

import com.sm.poke_features.listing.ui.ListingScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeListingFeatModule = module {
    viewModel { ListingScreenViewModel(get()) }
}