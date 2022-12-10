package i.am.bored.ui.newidea.fakes.interactors

import interactors.DeleteIdeaInteractor
import interactors.SaveIdeaInteractor
import model.IdeaDomain

class DeleteIdeaInteractorFake : DeleteIdeaInteractor {

    var wasCalled = false
        private set

    override suspend fun invoke(idea: IdeaDomain) {
        wasCalled = true
    }
}
