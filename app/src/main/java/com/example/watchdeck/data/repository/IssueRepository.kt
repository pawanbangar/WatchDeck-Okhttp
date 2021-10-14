package com.example.watchdeck.data.repository

import com.example.watchdeck.data.local.IssueDao
import com.example.watchdeck.data.remote.IssueRemoteDataSource
import com.example.watchdeck.utils.performGetOperation
import javax.inject.Inject

class IssueRepository @Inject constructor(
    private val remoteDataSource: IssueRemoteDataSource,
    private val localDataSource: IssueDao
) {

//    fun getIssue(number: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getIssue(number) },
//        networkCall = { remoteDataSource.getIssue(number) },
//        saveCallResult = { localDataSource.insert(it) }
//    )

    fun getIssues() = performGetOperation(
        databaseQuery = { localDataSource.getIssues() },
        networkCall = { remoteDataSource.getIssues() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

}