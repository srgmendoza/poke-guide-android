package com.sm.core.navigation.models

import com.sm.core.navigation.CoreNavigation

data class NavigationConfig(
    val startDestinationRoute: String,
    val featuresConfig: List<FeatureNavConfig>
)

data class FeatureNavConfig (
    val navInstance: CoreNavigation
)