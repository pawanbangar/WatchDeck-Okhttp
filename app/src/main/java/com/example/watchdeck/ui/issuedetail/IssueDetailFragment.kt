package com.example.watchdeck.ui.issuedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.watchdeck.data.entities.Comment
import com.example.watchdeck.databinding.IssueDetailFragmentBinding
import com.example.watchdeck.utils.Resource
import com.example.watchdeck.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueDetailFragment : Fragment() {

    private var binding: IssueDetailFragmentBinding by autoCleared()
    private val viewModel: IssueDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = IssueDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.comments.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindComment(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindComment(comment: List<Comment>) {
            Log.v("data",comment.toString());
//        binding.name.text = issue.name
//        binding.species.text = issue.species
//        binding.status.text = issue.status
//        binding.gender.text = issue.gender
//        Glide.with(binding.root)
//            .load(issue.image)
//            .transform(CircleCrop())
//            .into(binding.image)
    }
}
