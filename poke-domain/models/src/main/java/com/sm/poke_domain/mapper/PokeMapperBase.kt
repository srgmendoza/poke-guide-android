package com.sm.poke_domain.mapper

abstract class PokeMapperBase<DTO, DM> {
    abstract fun mapToDomainModel(dto: DTO): DM
}