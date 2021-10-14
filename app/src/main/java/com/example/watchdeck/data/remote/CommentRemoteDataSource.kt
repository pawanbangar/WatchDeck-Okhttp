package com.example.watchdeck.data.remote

import javax.inject.Inject

class CommentRemoteDataSource @Inject constructor(
    private val commentService: CommentService
): BaseDataSource() {
    suspend fun getComments(id: Int) = getResult { commentService.getComments(id) }
}