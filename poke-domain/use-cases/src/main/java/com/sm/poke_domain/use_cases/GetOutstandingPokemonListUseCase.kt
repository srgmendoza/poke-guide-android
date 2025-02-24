package com.sm.poke_domain.use_cases

import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeRefListRepository
import com.sm.poke_domain.models.PokemonListItemDomainModel

interface GetOutstandingPokemonListUseCase {
    suspend fun execute(): Result<List<PokemonListItemDomainModel>>
}

internal class GetOutstandingPokemonListUseCaseImpl(
    private val refRepo: PokeRefListRepository,
    private val detailRepo: PokeItemListRepository,
) : GetOutstandingPokemonListUseCase {

    override suspend fun execute(): Result<List<PokemonListItemDomainModel>> {
        val randomizedOffset = (2..1300).random()
        val result =
            refRepo.fetchPokemonList(offset = randomizedOffset, limit = 5).mapCatching { pokeReferenceDomainModel ->
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

        return result
    }
}