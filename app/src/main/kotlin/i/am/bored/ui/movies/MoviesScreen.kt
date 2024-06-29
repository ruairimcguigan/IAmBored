package i.am.bored.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.krzdabrowski.starter.core.utils.collectWithLifecycle
import i.am.bored.model.TopMovie
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MoviesScreen(
    uiState: MoviesUiState,
    onIntent: (MoviesIntent) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)
            .nestedScroll(pullToRefreshState.nestedScrollConnection),)
        {
            if (uiState.topMovies.isNotEmpty()) {

            }

        }

    }
}

@Composable
private fun MoviesAvailableContent(
    snackbarHostState: androidx.compose.material3.SnackbarHostState,
    uiState: MoviesUiState,
    onRocketClick: (String) -> Unit,
) {
    if (uiState.isError) {
        val errorMessage = stringResource(R.string.movies_error_refreshing)

        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
            )
        }
    }

    MoviesListContent(
        rocketList = uiState.topMovies,
        onRocketClick = onRocketClick,
    )
}

const val ROCKET_DIVIDER_TEST_TAG = "rocketDividerTestTag"

@Composable
fun MoviesListContent(
    rocketList: List<TopMovie>,
    modifier: Modifier = Modifier,
    onRocketClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.grid_16),
            ),
    ) {
        itemsIndexed(
            items = rocketList,
            key = { _, rocket -> rocket.id },
        ) { index, item ->
            RocketItem(
                movie = item,
                onRocketClick = { },
            )

            if (index < rocketList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.testTag(ROCKET_DIVIDER_TEST_TAG),
                )
            }
        }
    }
}

@Composable
fun RocketItem(
    movie: TopMovie,
    modifier: Modifier = Modifier,
    onRocketClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = i.am.bored.R.dimen.grid_16),
            )
            .clickable { onRocketClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_small),
            ),
        ) {
            Text(
                text = movie.title,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,)
            )
        }

        AsyncImage(
            model = rocket.imageUrl,
            contentDescription = stringResource(id = R.string.rocket_image_content_description),
            modifier = Modifier
                .weight(1f),
        )
    }
}


@Composable
fun MoviesRoute(viewModel: MoviesViewModel = hiltViewModel()) {
    HandleEvents(viewModel.getEvents())
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MoviesScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
    )
}

@Composable
private fun HandleEvents(events: Flow<MoviesEvent>) {
    val uriHandler = LocalUriHandler.current

    events.collectWithLifecycle {
        when (it) {
            is MoviesEvent.OpenWebBrowserWithDetails -> {
                uriHandler.openUri(it.uri)
            }
        }
    }
}
