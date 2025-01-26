package com.sm.poke_features.listing.di

import android.content.Context
import com.sm.poke_features.listing.ui.ListingScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeListingFeatModule = module {
    viewModel {
        ListingScreenViewModel(
            locale = getDeviceLocale(androidContext()),
            pagingHandler = get(),
            navigator = get()
        )
    }
}

private fun getDeviceLocale(context: Context): java.util.Locale {
    return context.resources.configuration.locales.get(0)
}