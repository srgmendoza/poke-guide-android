package com.sm.core.navigation

sealed class NavTVDestination(override val label: String): NavDestination {

    object HomeFeature : NavTVDestination(label = TVModuleRoutes.HOME_FEATURE.label)
    object HomeScreen : NavTVDestination(label = TVModuleRoutes.HOME_SCREEN.label)

    object SearchFeature : NavTVDestination(label = TVModuleRoutes.SEARCH_FEATURE.label)
    object SearchScreen : NavTVDestination(label = TVModuleRoutes.SEARCH_SCREEN.label)

    sealed class DetailFeature {
        data class DetailScreenWithId(val id: String) :
            NavTVDestination("${TVModuleRoutes.DETAIL_SCREEN.label}/$id")
    }
}

enum class TVModuleRoutes(val label: String) {
    HOME_FEATURE("homeTvFeatureDestination"),
    HOME_SCREEN("homeTvScreenDestination"),

    SEARCH_FEATURE("searchTvFeatureDestination"),
    SEARCH_SCREEN("searchTvScreenDestination"),

    DETAIL_FEATURE("detailTvFeatureDestination"),
    DETAIL_SCREEN("detailTvScreenDestination"),
}