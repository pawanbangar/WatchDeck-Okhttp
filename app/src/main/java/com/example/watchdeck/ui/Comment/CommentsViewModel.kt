package com.example.watchdeck.ui.Comment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.watchdeck.data.entities.Comment
import com.example.watchdeck.data.repository.CommentRepository
import com.example.watchdeck.utils.Resource

class CommentsViewModel @ViewModelInject constructor(
    private val repository: CommentRepository
) : ViewModel() {

    private val _data = MutableLiveData<Int>()

    private val _comments = _data.switchMap { id ->
        repository.getComments(id)
    }
    val comments: LiveData<Resource<List<Comment>>> = _comments


    fun start(id: Int) {
        _data.value = id
    }

}
