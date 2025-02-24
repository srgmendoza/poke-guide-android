package com.sm.pokeguide.app_tv.di

import com.sm.core.navigation.di.navigationModule
import org.koin.dsl.module

val mainTvModule =  module {}
    .plus(navigationModule)