package xyz.hirantha.testapplication.data.remote.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.hirantha.testapplication.models.Comment
import xyz.hirantha.testapplication.models.Post
import xyz.hirantha.testapplication.models.User

interface TypiCodeAPIService {

    @GET("/posts")
    fun getPosts(): Deferred<List<Post>>

    @GET("/posts/{id}")
    fun getPost(@Path("id") postId: Int): Deferred<Post>

    @GET("/users/{id}")
    fun getUser(@Path("id") userId: Int): Deferred<User>

    @GET("/posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Deferred<List<Comment>>

    @GET("/users")
    fun getUsers(): Deferred<List<User>>

    companion object {
        operator fun invoke(): TypiCodeAPIService {

            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TypiCodeAPIService::class.java)
        }
    }
}