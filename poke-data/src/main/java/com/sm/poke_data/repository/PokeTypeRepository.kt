package com.sm.poke_data.repository

import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeType.PokeTypeDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeReferenceInfoDomainModel
import com.sm.poke_domain.models.PokemonListByTypeDomainModel

interface PokeTypeRepository {
    suspend fun fetchPokeTypeById(
        typeId: String
    ): Result<PokemonListByTypeDomainModel>
}

internal class PokeTypeRepositoryImpl(private val pokeApi: PokeApi) : PokeTypeRepository {
    override suspend fun fetchPokeTypeById(
        typeId: String
    ): Result<PokemonListByTypeDomainModel> {
        try {
            val content =
                PokeTypeMapper().mapToDomainModel(pokeApi.getPokemonType(typeId = typeId))
            return Result.success(content)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}

internal class PokeTypeMapper :
    PokeMapperBase<PokeTypeDTO, PokemonListByTypeDomainModel>() {
    override fun mapToDomainModel(dto: PokeTypeDTO): PokemonListByTypeDomainModel {
        return PokemonListByTypeDomainModel(
            name = dto.name,
            topRelatedPokemons = dto.pokemonRefs.map {
                PokeReferenceInfoDomainModel(
                    name = it.pokemon.name,
                    url = it.pokemon.url
                )
            }
        )
    }
}