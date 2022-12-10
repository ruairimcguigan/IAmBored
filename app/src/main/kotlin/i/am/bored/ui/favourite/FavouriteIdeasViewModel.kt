package i.am.bored.ui.favourite

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import i.am.bored.ui.favourite.FavouriteIdeasUiState.Ideas
import i.am.bored.ui.favourite.FavouriteIdeasUiState.Empty
import interactors.DeleteIdeaInteractor
import interactors.GetFavouriteIdeasInteractor
import kotlinx.coroutines.launch
import model.IdeaDomain
import javax.inject.Inject

@HiltViewModel
class FavouriteIdeasViewModel @Inject constructor(
    getFavouriteIdeasInteractor: GetFavouriteIdeasInteractor,
    private val deleteIdeaInteractor: DeleteIdeaInteractor
) : ViewModel() {

    private var _ideasLivedata: MutableLiveData<FavouriteIdeasUiState> = MutableLiveData()
    val ideasLiveData: LiveData<FavouriteIdeasUiState>
        get() = _ideasLivedata

    init {
        viewModelScope.launch {
            when {
                getFavouriteIdeasInteractor().isEmpty() -> _ideasLivedata.postValue(Empty)
                else -> _ideasLivedata.postValue(Ideas(getFavouriteIdeasInteractor()))
            }
        }
    }

    fun deleteIdea(idea: IdeaDomain) {
        viewModelScope.launch {
            deleteIdeaInteractor.invoke(idea)
        }
    }
}
