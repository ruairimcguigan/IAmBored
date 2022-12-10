package interactors

import model.IdeaDomain

interface DeleteIdeaInteractor {
    suspend operator fun invoke(idea: IdeaDomain)
}
