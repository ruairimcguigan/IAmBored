package i.am.bored.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import i.am.bored.ui.theme.grid_1

@Composable
fun LoadingCard(
    modifier: Modifier
) {
    Card(
        modifier = modifier.aspectRatio(1.33f),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = grid_1
    ) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}