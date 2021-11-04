package xyz.hirantha.testapplication.data.db.dao

import androidx.room.*
import xyz.hirantha.testapplication.models.Comment

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertComments(comments: List<Comment>)
}