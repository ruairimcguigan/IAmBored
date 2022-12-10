package interactors

import model.IdeaDomain
import repository.IdeasRepository
import javax.inject.Inject

class GetFavouriteIdeasInteractorImpl @Inject constructor(
    private val activityRepository: IdeasRepository
) : GetFavouriteIdeasInteractor {

    override suspend fun invoke(): List<IdeaDomain> = activityRepository.getIdeasLiveData()
}
