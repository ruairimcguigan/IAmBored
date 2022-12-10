package interactors

interface IsIdeaSavedInteractor {
    suspend operator fun invoke(key: String): Boolean
}
