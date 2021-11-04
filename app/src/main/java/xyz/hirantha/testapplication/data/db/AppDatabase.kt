package xyz.hirantha.testapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.hirantha.testapplication.data.db.dao.CommentDao
import xyz.hirantha.testapplication.data.db.dao.PostDao
import xyz.hirantha.testapplication.data.db.dao.UserDao
import xyz.hirantha.testapplication.models.Comment
import xyz.hirantha.testapplication.models.Post
import xyz.hirantha.testapplication.models.User

@Database(
    entities = [Post::class, User::class, Comment::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app.db"
        )
            .build()
    }
}