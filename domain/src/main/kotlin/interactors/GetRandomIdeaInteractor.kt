package interactors

import model.IdeaDomain
import model.Result

interface GetRandomIdeaInteractor {
    suspend operator fun invoke(): Result<IdeaDomain>
}
