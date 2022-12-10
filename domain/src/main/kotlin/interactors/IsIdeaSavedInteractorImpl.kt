package interactors

import repository.IdeasRepository
import javax.inject.Inject

class IsIdeaSavedInteractorImpl @Inject constructor(
    private val activityRepository: IdeasRepository
) : IsIdeaSavedInteractor {

    override suspend fun invoke(key: String): Boolean {
        return activityRepository.isIdeaSaved(key)
    }
}
