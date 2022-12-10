package interactors

import model.IdeaDomain
import repository.IdeasRepository
import javax.inject.Inject

class SaveIdeaInteractorImpl @Inject constructor(
    private val activityRepository: IdeasRepository
) : SaveIdeaInteractor {

    override suspend fun invoke(idea: IdeaDomain) {
        activityRepository.saveIdea(idea)
    }
}
