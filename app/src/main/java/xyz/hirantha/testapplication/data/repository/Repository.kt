package xyz.hirantha.testapplication.data.repository

import androidx.lifecycle.LiveData
import xyz.hirantha.testapplication.models.PostAndUser
import xyz.hirantha.testapplication.models.PostAndUserWithComments
import xyz.hirantha.testapplication.models.User

interface Repository {
    suspend fun getPosts(): LiveData<List<PostAndUser>>
    suspend fun getPost(postId: Int): LiveData<PostAndUserWithComments>
    suspend fun getUser(userId: Int): LiveData<User>
}