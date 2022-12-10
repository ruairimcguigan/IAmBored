package i.am.bored.ui.newidea

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Bulb
import i.am.bored.R
import i.am.bored.ui.composables.LoadingCard
import i.am.bored.ui.composables.NewIdeaCard
import i.am.bored.ui.theme.*
import model.IdeaDomain
import model.Type

@Composable
fun NewIdeaScreen(
    modifier: Modifier = Modifier,
    viewModel: NewIdeasViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.padding(vertical = grid_8, horizontal = grid_6),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uiState = viewModel.uiState.collectAsState().value
        val uriHandler = LocalUriHandler.current

        Image(
            painter = painterResource(id = R.drawable.idea),
            contentDescription = null,
            modifier = Modifier
                .size(grid_40)
                .padding(grid_8),
        )
        when (uiState) {
            is NewIdeaUiState.Loading -> LoadingCard(modifier = Modifier.fillMaxWidth())
            is NewIdeaUiState.Success -> {
                NewIdeaCard(
                    modifier = Modifier.fillMaxWidth(),
                    idea = uiState.idea,
                    isFavourite = uiState.isFavourite,
                    onFavouriteClick = {
                        viewModel.setIsFavourite(uiState.idea, it)
                    },
                    onLinkClick = {
                        uriHandler.openUri(uiState.idea.link)
                    }
                )
            }
            is NewIdeaUiState.Error -> {
                Text(text = "Oops!: ${uiState.exception.message}")
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(
            modifier = Modifier.size(grid_21),
            onClick = { viewModel.loadNewIdea() },
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Icon(
                imageVector = EvaIcons.Outline.Bulb,
                modifier = Modifier.size(grid_21).padding(all = grid_5),
                contentDescription = stringResource(id = R.string.cd_refresh_idea),
                tint = Purple500
            )
        }
    }
}

@Preview
@Composable
fun Preview_NewIdeaCard() {
    val idea = IdeaDomain(
        name = "Learn to dance",
        type = Type.Charity,
        participantCount = 2,
        price = 0.1f,
        accessibility = 0.2f,
        key = "234244",
        link = "www.dance.com"
    )
    MaterialTheme {
        NewIdeaCard(
            modifier = Modifier.fillMaxWidth(),
            idea = idea,
            isFavourite = false,
            onFavouriteClick = { },
            onLinkClick = { }
        )
    }
}
