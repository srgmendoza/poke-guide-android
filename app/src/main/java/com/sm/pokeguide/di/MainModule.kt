package com.sm.pokeguide.di

import com.sm.core.navigation.di.navigationModule
import org.koin.dsl.module

val mainModule = module {}
    .plus(navigationModule)