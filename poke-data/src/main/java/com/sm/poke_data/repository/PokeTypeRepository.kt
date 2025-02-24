package com.sm.poke_data.repository

import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeType.PokeTypeDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeReferenceInfoDomainModel
import com.sm.poke_domain.models.PokemonListByTypeDomainModel

interface PokeTypeRepository {
    suspend fun fetchPokeTypeById(
        typeId: String,
        shouldTakeTop: Boolean = true
    ): Result<PokemonListByTypeDomainModel>
}

internal class PokeTypeRepositoryImpl(private val pokeApi: PokeApi) : PokeTypeRepository {
    override suspend fun fetchPokeTypeById(
        typeId: String,
        shouldTakeTop: Boolean
    ): Result<PokemonListByTypeDomainModel> {
        try {
            val content =
                PokeTypeMapper(shouldTakeTop).mapToDomainModel(pokeApi.getPokemonType(typeId = typeId))
            return Result.success(content)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}

internal class PokeTypeMapper(private val shouldTakeTop: Boolean) :
    PokeMapperBase<PokeTypeDTO, PokemonListByTypeDomainModel>() {
    override fun mapToDomainModel(dto: PokeTypeDTO): PokemonListByTypeDomainModel {
        return PokemonListByTypeDomainModel(
            name = dto.name,
            topRelatedPokemons = if(shouldTakeTop) {
                dto.pokemonRefs.drop(1).take(6).map {
                    PokeReferenceInfoDomainModel(
                        name = it.pokemon.name,
                        url = it.pokemon.url
                    )
                }
            } else {
                dto.pokemonRefs.take(12).map {
                    PokeReferenceInfoDomainModel(
                        name = it.pokemon.name,
                        url = it.pokemon.url
                    )
                }
            }
        )
    }
}