package com.sm.poke_domain.use_cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_domain.paging.ListingPagingHelper
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_domain.paging.ListingPagingHelper.Companion.OFFSET
import kotlinx.coroutines.flow.Flow

interface GetPokeListUseCase {
    suspend fun execute(): Flow<PagingData<PokemonListItemDomainModel>>
}

internal class GetPokeListUseCaseImpl(
    private val refRepo: PokeRefListRepository,
    private val detailRepo: PokeItemListRepository,
) : GetPokeListUseCase {

    override suspend fun execute(): Flow<PagingData<PokemonListItemDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = OFFSET
            ),
            pagingSourceFactory = {
                ListingPagingHelper(refRepo = refRepo, detailRepo = detailRepo)
            }
        ).flow
    }
}