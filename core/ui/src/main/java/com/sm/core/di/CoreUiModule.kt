package com.sm.core.di

import com.sm.core.commons.paging.ListingScreenPagingHandler
import com.sm.core.commons.paging.ListingScreenPagingHandlerImpl
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import org.koin.dsl.module

val pokeCoreUiModule = module {
    single {
        getPagingHandler(get())
    }
}

private fun getPagingHandler(useCase: GetPokeListUseCase): ListingScreenPagingHandler =
    ListingScreenPagingHandlerImpl(useCase)

