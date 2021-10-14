package com.example.watchdeck.di

import android.content.Context
import com.example.watchdeck.data.local.IssueDatabase
import com.example.watchdeck.data.local.CommentDao
import com.example.watchdeck.data.local.CommentDatabase
import com.example.watchdeck.data.local.IssueDao
import com.example.watchdeck.data.remote.CommentRemoteDataSource
import com.example.watchdeck.data.remote.CommentService
import com.example.watchdeck.data.remote.IssueRemoteDataSource
import com.example.watchdeck.data.remote.IssueService
import com.example.watchdeck.data.repository.CommentRepository
import com.example.watchdeck.data.repository.IssueRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/repos/square/okhttp/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideIssueService(retrofit: Retrofit): IssueService = retrofit.create(IssueService::class.java)

    @Provides
    fun provideCommentService(retrofit: Retrofit): CommentService = retrofit.create(CommentService::class.java)

    @Singleton
    @Provides
    fun provideIssueRemoteDataSource(issueService: IssueService) = IssueRemoteDataSource(issueService)

    @Singleton
    @Provides
    fun provideCommentRemoteDataSource(commentService: CommentService) = CommentRemoteDataSource(commentService)

    @Singleton
    @Provides
    fun provideIssueDatabase(@ApplicationContext appContext: Context) = IssueDatabase.getIssueDatabase(appContext)

    @Singleton
    @Provides
    fun provideCommentDatabase(@ApplicationContext appContext: Context) = CommentDatabase.getCommentDatabase(appContext)


    @Singleton
    @Provides
    fun provideCommentDao(db: CommentDatabase) = db.commentDao()

    @Singleton
    @Provides
    fun provideIssueDao(db: IssueDatabase) = db.issueDao()

    @Singleton
    @Provides
    fun provideIssueRepository(remoteDataSource: IssueRemoteDataSource,
                          localDataSource: IssueDao
    ) =
        IssueRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideCommentRepository(remoteDataSource: CommentRemoteDataSource,
                          localDataSource: CommentDao
    ) =
        CommentRepository(remoteDataSource, localDataSource)
}