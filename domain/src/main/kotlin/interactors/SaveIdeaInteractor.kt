package interactors

import model.IdeaDomain

interface SaveIdeaInteractor {
    suspend operator fun invoke(idea: IdeaDomain)
}
