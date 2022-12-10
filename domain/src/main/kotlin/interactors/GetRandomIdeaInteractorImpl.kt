package interactors

import model.IdeaDomain
import model.Result
import repository.IdeasRepository
import javax.inject.Inject

class GetRandomIdeaInteractorImpl @Inject constructor(
    private val ideasRepository: IdeasRepository
) : GetRandomIdeaInteractor {

    override suspend fun invoke(): Result<IdeaDomain> {
        return ideasRepository.getNewIdea()
    }
}
