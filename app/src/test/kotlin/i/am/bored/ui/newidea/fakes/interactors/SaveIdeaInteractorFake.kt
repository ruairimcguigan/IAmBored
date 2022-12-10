package i.am.bored.ui.newidea.fakes.interactors

import interactors.SaveIdeaInteractor
import model.IdeaDomain

class SaveIdeaInteractorFake : SaveIdeaInteractor {
    var wasCalled = false
        private set

    override suspend fun invoke(idea: IdeaDomain) {
        wasCalled = true
    }
}
