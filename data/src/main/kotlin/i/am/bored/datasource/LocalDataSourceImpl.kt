package i.am.bored.datasource

import i.am.bored.db.IdeaDao
import kotlinx.coroutines.flow.Flow
import repository.LocalDataSource
import repository.local.TopMovieCached
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val ideaDao: IdeaDao
) : LocalDataSource {
    override fun getTopRated(): Flow<List<TopMovieCached>> {
       // Not yet implemented
    }

    override suspend fun saveTopRatedMovies(movies: List<TopMovieCached>) {
        // "Not yet implemented
    }


}
