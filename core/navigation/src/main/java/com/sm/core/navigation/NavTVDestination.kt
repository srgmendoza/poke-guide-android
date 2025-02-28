package com.sm.core.navigation

sealed class NavTVDestination(override val label: String): NavDestination {

    object HomeFeature : NavTVDestination(label = TVModuleRoutes.HOME_FEATURE.label)
    object HomeScreen : NavTVDestination(label = TVModuleRoutes.HOME_SCREEN.label)

    object SearchFeature : NavTVDestination(label = TVModuleRoutes.SEARCH_FEATURE.label)
    object SearchScreen : NavTVDestination(label = TVModuleRoutes.SEARCH_SCREEN.label)

    object DetailFeature : NavTVDestination(label = TVModuleRoutes.DETAIL_FEATURE.label)
    object DetailScreen : NavTVDestination(label = TVModuleRoutes.DETAIL_SCREEN.label)
}

enum class TVModuleRoutes(val label: String) {
    HOME_FEATURE("homeTvFeatureDestination"),
    HOME_SCREEN("homeTvScreenDestination"),

    SEARCH_FEATURE("searchTvFeatureDestination"),
    SEARCH_SCREEN("searchTvScreenDestination"),

    DETAIL_FEATURE("detailTvFeatureDestination"),
    DETAIL_SCREEN("detailTvScreenDestination"),
}