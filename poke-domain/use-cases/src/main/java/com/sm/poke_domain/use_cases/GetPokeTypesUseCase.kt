package com.sm.poke_domain.use_cases

import com.sm.poke_data.repository.PokeTypeRepository
import com.sm.poke_domain.models.PokeTypeDomainModel
import com.sm.poke_domain.models.PokeTypesWithChildrenDomainModel

interface GetPokeTypesUseCase {
    suspend fun execute(): Result<List<PokeTypesWithChildrenDomainModel>>
}

internal class GetPokeTypesUseCaseImpl(
    private val repo: PokeTypeRepository,
    private val detailUseCase: GetPokeDetailUseCase
) : GetPokeTypesUseCase {
    private val typeRange = 1..19
    override suspend fun execute(): Result<List<PokeTypesWithChildrenDomainModel>> {
        val result = typeRange.map { typeId ->
            repo.fetchPokeTypeById(typeId = typeId.toString(), shouldTakeTop = false).mapCatching {
                PokeTypesWithChildrenDomainModel(
                    type = PokeTypeDomainModel(
                        name = it.name,
                        refId = it.name
                    ),
                    children = it.topRelatedPokemons.map { poke ->
                        detailUseCase.execute(poke.name).getOrThrow()
                    }
                )
            }
        }
        return Result.success(result.map { it.getOrThrow() })
    }
}
