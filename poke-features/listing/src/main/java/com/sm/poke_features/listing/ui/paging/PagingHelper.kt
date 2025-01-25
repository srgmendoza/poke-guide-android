package com.sm.poke_features.listing.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sm.poke_domain.models.PokemonListItemDomainModel
import com.sm.poke_domain.use_cases.GetPokeListUseCase

class ListingPagingHelper(
    private val useCase: GetPokeListUseCase,
) :
    PagingSource<Int, PokemonListItemDomainModel>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonListItemDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItemDomainModel> {
        return try {
            val position = params.key ?: 1

            return useCase.execute(position).fold(
                onSuccess = {
                    return@fold LoadResult.Page(
                        data = it,
                        prevKey = if (position == 1) null else position - 20,
                        nextKey = if (it.isEmpty()) null else position + 20,
                    )
                },
                onFailure = {
                    throw it
                }
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}