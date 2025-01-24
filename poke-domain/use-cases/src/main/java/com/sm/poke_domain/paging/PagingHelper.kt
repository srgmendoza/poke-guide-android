package com.sm.poke_domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_domain.models.PokemonListItemDomainModel

class ListingPagingHelper(
    private val refRepo: PokeRefListRepository,
    private val detailRepo: PokeItemListRepository,
) :
    PagingSource<Int, PokemonListItemDomainModel>() {

    companion object {
        const val OFFSET = 20
    }

    init {
        Log.d("ListingPagingHandler", "init")
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonListItemDomainModel>): Int? {
        return null /*state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }*/
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItemDomainModel> {
        return try {
            val position = params.key ?: 1

            // First we extract the result and process it within PokeDetails to obtain Images
            val result =
                refRepo.fetchPokemonList(offset = position - 1).mapCatching { pokeReferenceDomainModel ->
                    pokeReferenceDomainModel.refs.map { poke ->
                        detailRepo.fetchPokeItemByName(poke.name).map { detail ->
                            PokemonListItemDomainModel(
                                imageUrl = detail.imageUrl,
                                soundUrl = detail.soundUrl,
                                name = detail.name
                            )
                        }.getOrThrow()
                    }
                }

            // Next return the paged data
            return result.fold(
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