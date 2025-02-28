package com.sm.core.navigation

sealed class NavMobileDestination(override val label: String): NavDestination {
    object ListingFeature : NavMobileDestination(ModuleRoutes.ListingFeature.label)
    object ListingMainScreen : NavMobileDestination(ModuleRoutes.MainListingScreen.label)

    sealed class SearchFeature {
        data class SearchScreenWithText(val initialText: String) :
            NavMobileDestination("${ModuleRoutes.SearchScreen.label}/$initialText")
    }


    sealed class DetailFeature {
        data class PokeDetailScreen(val pokeName: String) :
            NavMobileDestination("${ModuleRoutes.MainDetailScreen.label}/$pokeName")
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