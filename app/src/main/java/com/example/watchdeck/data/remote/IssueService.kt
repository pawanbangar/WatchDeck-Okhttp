package com.example.watchdeck.data.remote

import com.example.watchdeck.data.entities.Issue
import retrofit2.Response
import retrofit2.http.GET

interface IssueService {
    @GET("issues")
    suspend fun getIssues() : Response<List<Issue>>

}