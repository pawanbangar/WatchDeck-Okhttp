package com.example.watchdeck.data.repository

import com.example.watchdeck.data.entities.Comment
import com.example.watchdeck.data.local.CommentDao
import com.example.watchdeck.data.remote.CommentRemoteDataSource
import com.example.watchdeck.utils.performGetOperation
import javax.inject.Inject

class CommentRepository @Inject constructor (
    private val remoteDataSource: CommentRemoteDataSource,
    private val localDataSource: CommentDao
) {
    fun getComments(id: Int)= performGetOperation(
        databaseQuery = { localDataSource.getComments(id)},
        networkCall = { remoteDataSource.getComments(id) },
        saveCallResult = { localDataSource.insertAll(it.map { Comment(id=id,body = it.body) }) }
    )
}