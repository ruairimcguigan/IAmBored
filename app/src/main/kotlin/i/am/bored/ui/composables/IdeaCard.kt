package i.am.bored.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import i.am.bored.R
import i.am.bored.ui.theme.grid_1
import i.am.bored.ui.theme.grid_18
import i.am.bored.ui.theme.grid_4
import model.IdeaDomain

@Composable
fun IdeaCard(
    modifier: Modifier,
    idea: IdeaDomain,
    onDeleteClick: (IdeaDomain) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(grid_18),
        elevation = grid_1
    ) {
        Row {
            Text(
                modifier = Modifier
                    .padding(horizontal = grid_4)
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically),
                text = idea.name
            )
            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                onClick = {
                    onDeleteClick(idea)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(id = R.string.cd_delete_idea)
                )
            }
        }

    }
}