package com.sm.poke_domain.use_cases

import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_data.repository.PokeTypeRepository
import com.sm.poke_domain.models.PokeDetailDomainModel

interface GetPokeSearchByName {
    suspend fun execute(name: String): Result<PokeDetailDomainModel>
}

internal class GetPokeSearchByNameImpl(
    private val detailRepo: PokeItemListRepository,
    private val typeRepository: PokeTypeRepository
) :
    GetPokeSearchByName {

    override suspend fun execute(name: String): Result<PokeDetailDomainModel> {
        val relatedPokemons = mutableListOf<PokeDetailDomainModel?>()
        val result = detailRepo.fetchPokeItemByName(name).mapCatching { poke ->
            poke.topType.refId?.let { typeId ->
                typeRepository.fetchPokeTypeById(typeId).map { type ->
                    type.topRelatedPokemons.map { relatedPoke ->
                        detailRepo.fetchPokeItemByName(relatedPoke.name).fold(
                            onSuccess = { result ->
                                relatedPokemons.add(result)
                            },
                            onFailure = {
                                relatedPokemons.add(null)
                            }
                        )
                    }
                }.getOrThrow()
            }
            poke.copy(
                relatedPokemons = relatedPokemons.filterNotNull()
            )
        }

        return result
    }
}