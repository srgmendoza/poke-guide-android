package com.sm.core.ui.commons.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_domain.use_cases.GetPokeListUseCase
import kotlinx.coroutines.flow.Flow

interface ListingScreenPagingHandler {
    fun getPagedData(): Flow<PagingData<PokemonListItemDomainModel>>
}

internal class ListingScreenPagingHandlerImpl(private val useCase: GetPokeListUseCase) :
    ListingScreenPagingHandler {

    companion object {
        const val OFFSET = 20
    }

    override fun getPagedData(): Flow<PagingData<PokemonListItemDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = OFFSET
            ),
            pagingSourceFactory = {
                ListingPagingHelper(
                    useCase = useCase
                )
            }
        ).flow
    }
}