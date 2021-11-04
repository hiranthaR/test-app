package xyz.hirantha.testapplication.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.hirantha.testapplication.data.db.dao.CommentDao
import xyz.hirantha.testapplication.data.db.dao.UserDao
import xyz.hirantha.testapplication.data.db.dao.PostDao
import xyz.hirantha.testapplication.data.remote.datasources.APIServiceDataSource
import xyz.hirantha.testapplication.models.*

class RepositoryImpl(
    private val remoteDataSource: APIServiceDataSource,
    private val postDao: PostDao,
    private val userDao: UserDao,
    private val commentDao: CommentDao,
) : Repository {

    init {
        remoteDataSource.apply {
            posts.observeForever {
                persistPosts(it)
            }
            users.observeForever {
                persistUsers(it)
            }

            post.observeForever {
                persistPost(it)
            }

            user.observeForever {
                persistUser(it)
            }

            comments.observeForever { persistComments(it) }
        }
    }

    private fun persistComments(comments: List<Comment>?) {
        GlobalScope.launch(Dispatchers.IO) {
            comments?.let { commentDao.upsertComments(it) }
        }
    }

    private fun persistUsers(users: List<User>?) {
        GlobalScope.launch(Dispatchers.IO) {
            users?.let { userDao.upsertUsers(it) }
        }
    }

    private fun persistPosts(posts: List<Post>?) {
        GlobalScope.launch(Dispatchers.IO) {
            posts?.let { postDao.upsertPosts(it) }
        }
    }

    private fun persistUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            userDao.upsertUser(user)
        }
    }

    private fun persistPost(post: Post) {
        GlobalScope.launch(Dispatchers.IO) {
            postDao.upsertPost(post)
        }
    }

    override suspend fun getPosts(): LiveData<List<PostAndUser>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getPosts()
            remoteDataSource.getUsers()
            return@withContext postDao.getPosts()
        }
    }

    override suspend fun getPost(postId: Int): LiveData<PostAndUserWithComments> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getPost(postId)
            remoteDataSource.getComments(postId)
            return@withContext postDao.getPost(postId)
        }
    }

    override suspend fun getUser(userId: Int): LiveData<User> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getUser(userId)
            return@withContext userDao.getUser(userId)
        }
    }
}