package com.sm.poke_features.detail.di

import com.sm.poke_features.detail.ui.PokemonDetailScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeDetailFeatModule = module {
    viewModel {
        PokemonDetailScreenViewModel(get())
    }
}