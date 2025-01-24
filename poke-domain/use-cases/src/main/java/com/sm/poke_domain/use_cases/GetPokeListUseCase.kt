package com.sm.poke_domain.use_cases

import com.sm.poke_data.repository.PokeNetworkRepository
import com.sm.poke_domain.models.PokeReferenceDomainModel


interface GetPokeListUseCase {
    suspend fun execute(): PokeReferenceDomainModel
}

internal class GetPokeListUseCaseImpl(
    private val repo: PokeNetworkRepository
) : GetPokeListUseCase {
    override suspend fun execute(): PokeReferenceDomainModel {
        return repo.fetchPokemonList()
    }
}