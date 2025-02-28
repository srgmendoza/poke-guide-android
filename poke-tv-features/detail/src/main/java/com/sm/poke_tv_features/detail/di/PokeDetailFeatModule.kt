package com.sm.poke_tv_features.detail.di

import com.sm.poke_tv_features.detail.ui.DetailScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeTvDetailFeatModule = module {
    viewModel {
        DetailScreenViewModel(get(), get())
    }
}