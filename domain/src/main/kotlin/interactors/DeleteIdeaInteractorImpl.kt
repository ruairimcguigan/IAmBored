package interactors

import model.IdeaDomain
import repository.IdeasRepository
import javax.inject.Inject

class DeleteIdeaInteractorImpl @Inject constructor(
    private val ideasRepository: IdeasRepository
) : DeleteIdeaInteractor {

    override suspend fun invoke(idea: IdeaDomain) {
        ideasRepository.deleteIdea(idea)
    }
}
