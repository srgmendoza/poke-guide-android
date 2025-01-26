package com.sm.core.navigation

sealed class NavDestination(val label: String) {
    object ListingFeature : NavDestination(ModuleRoutes.ListingFeature.label)
    object ListinMainScreen : NavDestination(ModuleRoutes.MainListingScreen.label)

    sealed class SearchFeature {
        data class SearchFeature(val initialText: String) :
            NavDestination("${ModuleRoutes.SearchFeature.label}/$initialText")

        data class SearchScreenWithText(val initialText: String) :
            NavDestination("${ModuleRoutes.SearchScreen.label}/$initialText")
    }


    sealed class Detail {
        data class PokeDetail(val pokeId: String) :
            NavDestination("${ModuleRoutes.Detail.label}/$pokeId")
    }
}

enum class ModuleRoutes(val label: String) {
    ListingFeature(label = "listingFeature"),
    MainListingScreen(label = "mainListingScreen"),

    SearchFeature(label = "searchFeature"),
    SearchScreen(label = "searchScreen"),

    Detail(label = "detailFeature"),
    MainDetailScreen(label = "MainListingScreen")
}