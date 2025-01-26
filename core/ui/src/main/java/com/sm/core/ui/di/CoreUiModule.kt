package com.sm.core.ui.di

import com.sm.core.ui.commons.paging.ListingScreenPagingHandler
import com.sm.core.ui.commons.paging.ListingScreenPagingHandlerImpl
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import org.koin.dsl.module

val pokeCoreUiModule = module {
    single {
        getPagingHandler(get())
    }
}

private fun getPagingHandler(useCase: GetPokeListUseCase): ListingScreenPagingHandler =
    ListingScreenPagingHandlerImpl(useCase)

