package com.example.watchdeck.ui.issues

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.watchdeck.data.repository.IssueRepository

class IssuesViewModel @ViewModelInject constructor(
    private val repository: IssueRepository
) : ViewModel() {

    val issues = repository.getIssues()
}
