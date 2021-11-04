package xyz.hirantha.testapplication.data.remote.datasources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.hirantha.testapplication.data.remote.api.TypiCodeAPIService
import xyz.hirantha.testapplication.models.Comment
import xyz.hirantha.testapplication.models.Post
import xyz.hirantha.testapplication.models.User

class APIServiceDataSourceImpl(
    private val typiCodeAPIService: TypiCodeAPIService,
) :
    APIServiceDataSource {

    override val posts: LiveData<List<Post>>
        get() = _posts
    private val _posts = MutableLiveData<List<Post>>()

    override val users: LiveData<List<User>>
        get() = _users
    private val _users = MutableLiveData<List<User>>()

    override val post: LiveData<Post>
        get() = _post
    private val _post = MutableLiveData<Post>()

    override val user: LiveData<User>
        get() = _user
    private val _user = MutableLiveData<User>()

    override val comments: LiveData<List<Comment>>
        get() = _comments
    private val _comments = MutableLiveData<List<Comment>>()

    override suspend fun getPosts() {
        try {
            val posts = typiCodeAPIService.getPosts().await()
            _posts.postValue(posts)
        } catch (e: Exception) {
            Log.d("API Service", e.message + "")
            e.printStackTrace()
        }
    }

    override suspend fun getUsers() {
        try {
            val users = typiCodeAPIService.getUsers().await()
            _users.postValue(users)
        } catch (e: Exception) {
            Log.d("API Service", e.message + "")
            e.printStackTrace()
        }
    }

    override suspend fun getPost(postId: Int) {
        try {
            val post = typiCodeAPIService.getPost(postId).await()
            _post.postValue(post)
        } catch (e: Exception) {
            Log.d("API Service", e.message + "")
            e.printStackTrace()
        }
    }

    override suspend fun getUser(userId: Int) {
        try {
            val user = typiCodeAPIService.getUser(userId).await()
            _user.postValue(user)
        } catch (e: Exception) {
            Log.d("API Service", e.message + "")
            e.printStackTrace()
        }
    }

    override suspend fun getComments(postId: Int) {
        try {
            val comments = typiCodeAPIService.getComments(postId).await()
            _comments.postValue(comments)
        } catch (e: Exception) {
            Log.d("API Service", e.message + "")
            e.printStackTrace()
        }
    }
}