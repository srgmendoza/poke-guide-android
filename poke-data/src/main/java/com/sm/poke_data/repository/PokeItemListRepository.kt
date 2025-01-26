package com.sm.poke_data.repository

import android.net.Uri
import com.sm.poke_data.api.PokeApi
import com.sm.poke_data.dto.pokeList.PokeDataDTO
import com.sm.poke_domain.mapper.PokeMapperBase
import com.sm.poke_domain.models.PokeDetailDomainModel
import com.sm.poke_domain.models.PokeTypeDomainModel

interface PokeItemListRepository {
    suspend fun fetchPokeItemByName(name: String): Result<PokeDetailDomainModel>
}

internal class PokeItemListRepositoryImpl(private val pokeApi: PokeApi) : PokeItemListRepository {
    override suspend fun fetchPokeItemByName(name: String): Result<PokeDetailDomainModel> {
        try {
            val content =
                PokeDetailMapper().mapToDomainModel(pokeApi.getPokemonDetail(name))
            return Result.success(content)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}

internal class PokeDetailMapper :
    PokeMapperBase<PokeDataDTO, PokeDetailDomainModel>() {
    override fun mapToDomainModel(dto: PokeDataDTO): PokeDetailDomainModel {
        return PokeDetailDomainModel(
            id = dto.id.toString(),
            imageUrl = dto.sprites.other?.officialArtwork?.frontDefault,
            name = dto.name,
            height = dto.height,
            weight = dto.weight,
            soundUrl = dto.cries.latest,
            topType = PokeTypeDomainModel(
                name = dto.types.firstOrNull()?.type?.name,
                refId = getId(dto.types.firstOrNull()?.type?.url)
            )
        )
    }

    private fun getId(url: String?): String? {
        if (url == null) return null
        val uri = Uri.parse(url)
        return uri.lastPathSegment
    }
}