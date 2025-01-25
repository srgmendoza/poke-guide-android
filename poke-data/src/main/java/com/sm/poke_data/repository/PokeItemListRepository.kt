package com.sm.poke_data.repository

import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeList.PokeDataDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeDetailDomainModel

interface PokeItemListRepository {
    suspend fun fetchPokeItemByName(name: String): Result<PokeDetailDomainModel>
}

internal class PokeItemListRepositoryImpl(private val pokeApi: PokeApi) : PokeItemListRepository {
    override suspend fun fetchPokeItemByName(name: String): Result<PokeDetailDomainModel> {
        try {
            val content = PokeDetailMapper(pokemonName = name).mapToDomainModel(pokeApi.getPokemonDetail(name))
            return Result.success(content)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}

internal class PokeDetailMapper(private val pokemonName: String) :
    PokeMapperBase<PokeDataDTO, PokeDetailDomainModel>() {
    override fun mapToDomainModel(dto: PokeDataDTO): PokeDetailDomainModel {
        return PokeDetailDomainModel(
            imageUrl = dto.sprites.other?.officialArtwork?.frontDefault,
            name = pokemonName,
            soundUrl = dto.cries.latest
        )
    }
}