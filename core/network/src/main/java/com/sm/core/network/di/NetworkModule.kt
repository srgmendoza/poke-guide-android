package com.sm.core.network.di

import com.sm.core.network.NetworkCore
import com.sm.core.network.NetworkCoreImpl
import org.koin.dsl.module

val networkCoreModule = module {
    factory<NetworkCore> {
        NetworkCoreImpl()
    }
}
