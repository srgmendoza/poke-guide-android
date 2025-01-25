package com.sm.poke_features.listing.di

import android.content.Context
import android.os.Build
import androidx.compose.ui.text.intl.Locale
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import com.sm.poke_features.listing.ui.ListingScreenViewModel
import com.sm.poke_features.listing.ui.paging.ListingScreenPagingHandler
import com.sm.poke_features.listing.ui.paging.ListingScreenPagingHandlerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokeListingFeatModule = module {
    viewModel {
        ListingScreenViewModel(
            locale = getDeviceLocale(androidContext()),
            pagingHandler = get()
        )
    }
    single {
        getPagingHandler(get())
    }
}

private fun getPagingHandler(useCase: GetPokeListUseCase): ListingScreenPagingHandler =
    ListingScreenPagingHandlerImpl(useCase)


private fun getDeviceLocale(context: Context): java.util.Locale {
    return context.resources.configuration.locales.get(0)
}