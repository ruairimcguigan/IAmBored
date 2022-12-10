package interactors

import model.IdeaDomain

interface GetFavouriteIdeasInteractor {
    suspend operator fun invoke(): List<IdeaDomain>
}
