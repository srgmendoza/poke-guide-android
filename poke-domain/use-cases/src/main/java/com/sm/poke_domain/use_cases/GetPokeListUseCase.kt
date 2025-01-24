package com.sm.poke_domain.use_cases

import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_domain.models.PokemonListItemDomainModel

interface GetPokeListUseCase {
    suspend fun execute(): Result<List<PokemonListItemDomainModel>>
}

internal class GetPokeListUseCaseImpl(
    private val refRepo: PokeRefListRepository,
    private val detailRepo: PokeItemListRepository,
) : GetPokeListUseCase {

    override suspend fun execute(): Result<List<PokemonListItemDomainModel>> {
        return refRepo.fetchPokemonList().mapCatching { pokeReferenceDomainModel ->
            pokeReferenceDomainModel.results.map { poke ->
                detailRepo.fetchPokeItemByName(poke.name).map { detail ->
                    PokemonListItemDomainModel(
                        imageUrl = detail.imageUrl,
                        soundUrl = detail.soundUrl,
                        name = detail.name
                    )
                }.getOrThrow() // Re-throw exceptions if detail fetch fails
            }
        }
    }
}