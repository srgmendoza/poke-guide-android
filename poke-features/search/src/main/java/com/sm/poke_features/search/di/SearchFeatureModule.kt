package com.sm.poke_features.search.di

import com.sm.poke_features.search.ui.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchFeatureModule = module {
    viewModel {
        SearchScreenViewModel(get())
    }
}