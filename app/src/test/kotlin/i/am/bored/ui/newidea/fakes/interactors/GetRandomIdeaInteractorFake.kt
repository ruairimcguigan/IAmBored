package i.am.bored.ui.newidea.fakes.interactors

import i.am.bored.ui.newidea.fakes.models.firstIdea
import interactors.GetRandomIdeaInteractor
import model.IdeaDomain
import model.Result
import java.lang.RuntimeException

class GetRandomIdeaInteractorFake(
    private val isSuccessful: Boolean = true
) : GetRandomIdeaInteractor {

    var idea: IdeaDomain? = null

    override suspend fun invoke(): Result<IdeaDomain> {
        return if (isSuccessful) Result.Success(idea ?: firstIdea)
        else Result.Error(RuntimeException("merde"))
    }
}
