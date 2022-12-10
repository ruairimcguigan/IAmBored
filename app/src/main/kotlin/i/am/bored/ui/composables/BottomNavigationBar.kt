package i.am.bored.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import i.am.bored.navigation.Navigator

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentNavigator: Navigator,
    onNavigate: (navigator: Navigator) -> Unit
) {
    BottomNavigation(
        modifier = modifier
    ) {
        listOf(
            Navigator.Ideas,
            Navigator.Favourites
        ).forEach { destination ->
            BottomNavigationItem(
                selected = currentNavigator.path == destination.path,
                icon = {
                    destination.icon?.let { image ->
                        Icon(
                            imageVector = image,
                            contentDescription = destination.path
                        )
                    }
                },
                onClick = {
                    onNavigate(destination)
                },
                label = {
                    Text(text = destination.title)
                }
            )
        }
    }
}