package i.am.bored.navigation

sealed class NavigationDestination(
    val route: String,
) {
    data object Movies : NavigationDestination("moviesDestination")

    data object Back : NavigationDestination("navigationBack")
}
