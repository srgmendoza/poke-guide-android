package com.sm.poke_domain.use_cases

import com.sm.poke_data.repository.PokeItemListRepository
import com.sm.poke_domain.models.PokeDetailDomainModel

interface GetPokeDetailUseCase {
    suspend fun execute(name: String): Result<PokeDetailDomainModel>
}

internal class GetPokeDetailUseCaseImpl(private val repository: PokeItemListRepository): GetPokeDetailUseCase {
    override suspend fun execute(name: String): Result<PokeDetailDomainModel> {
        return repository.fetchPokeItemByName(name)
    }
}