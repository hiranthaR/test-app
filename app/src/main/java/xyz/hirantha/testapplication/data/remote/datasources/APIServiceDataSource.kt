package xyz.hirantha.testapplication.data.remote.datasources

import androidx.lifecycle.LiveData
import xyz.hirantha.testapplication.models.Comment
import xyz.hirantha.testapplication.models.Post
import xyz.hirantha.testapplication.models.User

interface APIServiceDataSource {

    val posts: LiveData<List<Post>>
    val comments: LiveData<List<Comment>>
    val users: LiveData<List<User>>
    val post: LiveData<Post>
    val user: LiveData<User>

    suspend fun getPosts()
    suspend fun getUsers()
    suspend fun getPost(postId: Int)
    suspend fun getUser(userId: Int)
    suspend fun getComments(postId: Int)

}