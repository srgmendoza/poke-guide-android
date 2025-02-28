package com.sm.poke_tv_features.search.di

import com.sm.poke_tv_features.search.ui.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeTvSearchFeatModule = module {
    viewModel { SearchScreenViewModel(get(), get()) }
}