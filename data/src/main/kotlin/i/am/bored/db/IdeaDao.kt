package i.am.bored.db

import androidx.lifecycle.LiveData
import androidx.room.*
import i.am.bored.model.Idea

@Dao
interface IdeaDao {
    @Query("SELECT * FROM idea")
    suspend fun getAll(): List<Idea>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(idea: Idea)

    @Delete
    suspend fun delete(idea: Idea)

    @Query("SELECT EXISTS(SELECT * FROM idea WHERE `key` = :key)")
    suspend fun isIdeaSaved(key : String) : Boolean
}
