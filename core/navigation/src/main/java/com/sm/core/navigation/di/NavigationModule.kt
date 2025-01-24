package com.sm.core.navigation.di

import com.sm.core.navigation.Navigator
import com.sm.core.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    single { getNavigator() }
}

private fun getNavigator(): Navigator = NavigatorImpl()