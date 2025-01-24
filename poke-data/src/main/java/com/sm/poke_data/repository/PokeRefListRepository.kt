package com.sm.poke_data.repository

import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeReferences.PokeReferenceDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeReferenceDomainModel
import com.sm.poke_domain.models.ResultDomainModel

interface PokeRefListRepository {
    suspend fun fetchPokemonList(): Result<PokeReferenceDomainModel>
}

internal class PokeRefListRepositoryImpl(private val pokeApi: PokeApi) : PokeRefListRepository {
    override suspend fun fetchPokemonList(): Result<PokeReferenceDomainModel> {
        try {
            val content = PokeRefListMap().mapToDomainModel(pokeApi.getPokemonReferenceList())
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
            results = dto.results.map {
                ResultDomainModel(
                    name = it.name,
                    url = it.url
                )
            }
        )
    }
}
