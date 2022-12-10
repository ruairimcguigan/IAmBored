package i.am.bored.ui.newidea

import model.IdeaDomain

sealed class NewIdeaUiState {
    data class Success(val idea: IdeaDomain, val isFavourite: Boolean): NewIdeaUiState()
    data class Error(val exception: Throwable): NewIdeaUiState()
    object Loading : NewIdeaUiState()
}
