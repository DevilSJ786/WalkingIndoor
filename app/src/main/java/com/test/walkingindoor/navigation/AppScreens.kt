package com.test.walkingindoor.navigation


enum class AppScreens {
    HomeScreen,
    WalkingtypesScreen,
    GoalsScreen
    ;

    companion object {
        fun fromRoute(route: String?): AppScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                WalkingtypesScreen.name -> WalkingtypesScreen
                GoalsScreen.name->GoalsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not valid")
            }

    }
}