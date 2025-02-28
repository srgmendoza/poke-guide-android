package com.sm.poke_tv_features.listing.di

import com.sm.poke_domain.di.pokeDomainModule
import com.sm.poke_tv_features.listing.ui.MainHomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeHomeTvFeatModule = pokeDomainModule.plus(
    module {
        viewModel {
            MainHomeScreenViewModel(
                outstandingUseCase = get(),
                ribbonsUseCase = get(),
                navigator = get()
            )
        }
    }
)