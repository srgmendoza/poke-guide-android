package com.sm.poke_data.repository

import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeReferences.PokeReferenceDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeReferenceDomainModel
import com.sm.poke_domain.models.PokeReferenceInfoDomainModel

interface PokeRefListRepository {
    suspend fun fetchPokemonList(offset: Int): Result<PokeReferenceDomainModel>
}

internal class PokeRefListRepositoryImpl(private val pokeApi: PokeApi) : PokeRefListRepository {
    companion object {
        private const val ITEMS_LIMIT = 20
        private const val FIRST_QUERY_ITEMS_COUNT = 20
    }

    override suspend fun fetchPokemonList(offset: Int): Result<PokeReferenceDomainModel> {
        try {
            val content = PokeRefListMap()
                .mapToDomainModel(
                    pokeApi.getPokemonReferenceList(
                        offset = offset.takeIf { it > 1 },
                        limit = ITEMS_LIMIT.takeIf { offset > 1 }
                    )
                )
            return Result.success(content)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}

internal class PokeRefListMap : PokeMapperBase<PokeReferenceDTO, PokeReferenceDomainModel>() {
    override fun mapToDomainModel(dto: PokeReferenceDTO): PokeReferenceDomainModel {
        return PokeReferenceDomainModel(
            count = dto.count,
            next = dto.next,
            previous = dto.previous,
            refs = dto.results.map {
                PokeReferenceInfoDomainModel(
                    name = it.name,
                    url = it.url
                )
            }
        )
    }
}
