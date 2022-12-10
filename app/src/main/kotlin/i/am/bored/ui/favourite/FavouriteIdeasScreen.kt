package i.am.bored.ui.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import i.am.bored.R
import i.am.bored.ui.composables.IdeaCard
import i.am.bored.ui.theme.grid_2
import i.am.bored.ui.theme.grid_4
import i.am.bored.ui.theme.grid_6
import i.am.bored.ui.theme.sp_5
import model.IdeaDomain

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteIdeasViewModel = hiltViewModel()
) {
    val ideas = remember { mutableStateListOf<IdeaDomain>() }
    var isSavedIdeaEmpty by remember { mutableStateOf(false) }

    when {
        isSavedIdeaEmpty -> NoItemsInfo(modifier = modifier)
        else -> when (val uiState = viewModel.ideasLiveData.observeAsState().value) {
            is FavouriteIdeasUiState.Ideas -> {
                ideas.addAll(uiState.ideaList)
                IdeasList(
                    modifier = modifier,
                    ideaList = ideas,
                    onDeleteClick = {
                        viewModel.deleteIdea(it)
                        ideas.remove(it)
                        if (ideas.isEmpty()) isSavedIdeaEmpty = true
                    }
                )
            }
            is FavouriteIdeasUiState.Empty, null -> NoItemsInfo(modifier = modifier)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IdeasList(
    modifier: Modifier,
    ideaList: List<IdeaDomain>,
    onDeleteClick: (IdeaDomain) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = grid_4, vertical = grid_6),
        verticalArrangement = Arrangement.spacedBy(grid_2),
    ) {
        items(ideaList, key = { item -> item.key }) { idea ->
            IdeaCard(
                modifier = Modifier.animateItemPlacement(),
                idea = idea,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun NoItemsInfo(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.message_empty_idea_list),
            textAlign = TextAlign.Center,
            fontSize = sp_5,
        )
    }
}
