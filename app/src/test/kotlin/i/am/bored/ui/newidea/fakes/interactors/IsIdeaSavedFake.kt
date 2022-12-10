package i.am.bored.ui.newidea.fakes.interactors

import interactors.IsIdeaSavedInteractor

class IsIdeaSavedFake(private val isIdeaSaved: Boolean = false): IsIdeaSavedInteractor {
    override suspend fun invoke(key: String): Boolean = isIdeaSaved
}