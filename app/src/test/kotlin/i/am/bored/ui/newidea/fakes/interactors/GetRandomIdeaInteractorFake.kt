package i.am.bored.ui.newidea.fakes.interactors

import i.am.bored.ui.newidea.fakes.models.firstIdea
import model.IdeaDomain
import model.Response
import java.lang.RuntimeException

class GetRandomIdeaInteractorFake(
    private val isSuccessful: Boolean = true
) : GetRandomIdeaInteractor {

    var idea: IdeaDomain? = null

    override suspend fun invoke(): Response<IdeaDomain> {
        return if (isSuccessful) Response.Success(idea ?: firstIdea)
        else Response.Error(RuntimeException("merde"))
    }
}
