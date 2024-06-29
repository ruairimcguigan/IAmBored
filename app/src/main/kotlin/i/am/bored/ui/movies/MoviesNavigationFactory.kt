package i.am.bored.ui.movies

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import i.am.bored.navigation.NavigationDestination.Movies
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import javax.inject.Inject

class MoviesNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(Movies.route) {
            MoviesRoute()
        }
    }
}
