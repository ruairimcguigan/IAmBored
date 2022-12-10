package i.am.bored.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navigator(
    val path: String,
    val icon: ImageVector? = null
) {
    companion object {
        fun fromString(route: String): Navigator {
            return when (route) {
                Ideas.path -> Ideas
                Favourites.path -> Favourites
                else -> Home
            }
        }
    }

    object Home : Navigator("home")
    object Ideas : Navigator("ideas", Icons.Filled.Refresh)
    object Favourites : Navigator("favourites", Icons.Filled.Star)

    val title = path.replaceFirstChar(Char::uppercase)
}