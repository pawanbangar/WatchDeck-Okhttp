package com.example.watchdeck.data.remote

import com.example.watchdeck.data.entities.Comment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {

    @GET("issues/{id}/comments")
    suspend fun getComments(@Path("id") id: Int) : Response<List<Comment>>
}