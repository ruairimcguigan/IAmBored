package i.am.bored.ui.favourite

import model.IdeaDomain

sealed class FavouriteIdeasUiState {
    data class Ideas(val ideaList: List<IdeaDomain>): FavouriteIdeasUiState()
    object Empty : FavouriteIdeasUiState()
}
