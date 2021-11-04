package xyz.hirantha.testapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.hirantha.testapplication.data.db.AppDatabase
import xyz.hirantha.testapplication.data.db.dao.CommentDao
import xyz.hirantha.testapplication.data.db.dao.PostDao
import xyz.hirantha.testapplication.data.db.dao.UserDao
import xyz.hirantha.testapplication.data.remote.api.TypiCodeAPIService
import xyz.hirantha.testapplication.data.remote.datasources.APIServiceDataSource
import xyz.hirantha.testapplication.data.remote.datasources.APIServiceDataSourceImpl
import xyz.hirantha.testapplication.data.repository.Repository
import xyz.hirantha.testapplication.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = AppDatabase(context)

    @Singleton
    @Provides
    fun providePostDao(
        appDatabase: AppDatabase
    ) = appDatabase.postDao()

    @Singleton
    @Provides
    fun provideUserDao(
        appDatabase: AppDatabase
    ) = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideCommentDao(
        appDatabase: AppDatabase
    ) = appDatabase.commentDao()

    @Singleton
    @Provides
    fun provideApiService(): TypiCodeAPIService = TypiCodeAPIService()

    @Singleton
    @Provides
    fun provideApiDataSource(
        apiService: TypiCodeAPIService
    ): APIServiceDataSource = APIServiceDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideRepository(
        apiDataSource: APIServiceDataSource,
        postDao: PostDao,
        userDao: UserDao,
        commentDao: CommentDao
    ): Repository = RepositoryImpl(apiDataSource,postDao,userDao, commentDao)
}