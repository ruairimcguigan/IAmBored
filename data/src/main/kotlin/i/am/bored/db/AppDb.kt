package i.am.bored.db

import androidx.room.Database
import androidx.room.RoomDatabase
import i.am.bored.db.IdeaDao
import i.am.bored.model.Idea

@Database(entities = [Idea::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun ideaDao(): IdeaDao
}
