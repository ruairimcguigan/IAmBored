package i.am.bored.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import i.am.bored.ui.favourite.FavouritesScreen
import i.am.bored.ui.newidea.NewIdeaScreen
import i.am.bored.navigation.Navigator

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Navigator.Home.path
    ) {
        navigation(
            startDestination = Navigator.Ideas.path,
            route = Navigator.Home.path
        ) {
            composable(Navigator.Ideas.path) {
                NewIdeaScreen(modifier = modifier)
            }
            composable(Navigator.Favourites.path) {
                FavouritesScreen(modifier = modifier)
            }
        }
    }
}
