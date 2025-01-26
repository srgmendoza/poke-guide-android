package com.sm.core.navigation

sealed class NavDestination(val label: String) {
    object ListingFeature : NavDestination(ModuleRoutes.ListingFeature.label)
    object ListingMainScreen : NavDestination(ModuleRoutes.MainListingScreen.label)

    sealed class SearchFeature {
        data class SearchScreenWithText(val initialText: String) :
            NavDestination("${ModuleRoutes.SearchScreen.label}/$initialText")
    }


    sealed class DetailFeature {
        data class PokeDetailScreen(val pokeName: String) :
            NavDestination("${ModuleRoutes.MainDetailScreen.label}/$pokeName")
    }
}

enum class ModuleRoutes(val label: String) {
    ListingFeature(label = "listingFeature"),
    MainListingScreen(label = "mainListingScreen"),

    SearchFeature(label = "searchFeature"),
    SearchScreen(label = "searchScreen"),

    DetailFeature(label = "detailFeature"),
    MainDetailScreen(label = "mainDetailScreen")
}