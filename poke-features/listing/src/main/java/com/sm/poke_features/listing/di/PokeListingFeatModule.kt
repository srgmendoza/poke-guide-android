package com.sm.poke_features.listing.di

import com.sm.poke_domain.use_cases.GetPokeListUseCase
import com.sm.poke_features.listing.ui.ListingScreenViewModel
import com.sm.poke_features.listing.ui.paging.ListingScreenPagingHandler
import com.sm.poke_features.listing.ui.paging.ListingScreenPagingHandlerImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeListingFeatModule = module {
    viewModel { ListingScreenViewModel(get()) }
    single {
        getPagingHandler(get())
    }
}

private fun getPagingHandler(useCase: GetPokeListUseCase): ListingScreenPagingHandler =
    ListingScreenPagingHandlerImpl(useCase)