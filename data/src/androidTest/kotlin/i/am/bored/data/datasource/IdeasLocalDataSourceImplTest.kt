package i.am.bored.data.datasource

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import i.am.bored.data.testmodels.androidFirstIdea
import i.am.bored.datasource.LocalDataSourceImpl
import i.am.bored.db.AppDb
import i.am.bored.db.IdeaDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class IdeasLocalDataSourceImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var ideasDao: IdeaDao
    private lateinit var db: AppDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
        ideasDao = db.ideaDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun canSaveActivityToTheDbAndReadIt() = runTest {
        // given
        val activityLocalDataSource = LocalDataSourceImpl(ideasDao)

        // when
        activityLocalDataSource.saveIdea(androidFirstIdea)

        // then
        assert(activityLocalDataSource.isIdeaSaved(androidFirstIdea.key))
    }

    @Test
    fun canDeleteActivityFromTheDb() = runTest {
        // given
        val ideasLocalDataSource = LocalDataSourceImpl(ideasDao)
        ideasLocalDataSource.saveIdea(androidFirstIdea)

        // when
        ideasLocalDataSource.deleteIdea(androidFirstIdea)

        // then
        assert(!ideasLocalDataSource.isIdeaSaved(androidFirstIdea.key))
    }
}
