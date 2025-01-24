package com.sm.poke_data.repository

import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeReferences.PokeReferenceDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeReferenceDomainModel
import com.sm.poke_domain.models.ResultDomainModel

interface PokeNetworkRepository {
    suspend fun fetchPokemonList(): PokeReferenceDomainModel
}

internal class PokeNetworkRepositoryImpl(private val pokeApi: PokeApi) : PokeNetworkRepository {
    override suspend fun fetchPokemonList(): PokeReferenceDomainModel {

        return PokeListMap().mapToDomainModel(pokeApi.getPokemonList())
    }
}

internal class PokeListMap : PokeMapperBase<PokeReferenceDTO, PokeReferenceDomainModel>() {
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
