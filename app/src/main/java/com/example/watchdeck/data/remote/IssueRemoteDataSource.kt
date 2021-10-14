package com.example.watchdeck.data.remote

import javax.inject.Inject

class IssueRemoteDataSource @Inject constructor(
    private val issueService: IssueService
): BaseDataSource() {

    suspend fun getIssues() = getResult { issueService.getIssues() }

}