package com.example.watchdeck.ui.Comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchdeck.databinding.CommentFragmentBinding
import com.example.watchdeck.utils.Resource
import com.example.watchdeck.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {

    private var binding: CommentFragmentBinding by autoCleared()
    private val viewModel: CommentsViewModel by viewModels()
    private lateinit var adapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CommentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        binding.title.text=arguments?.getString("info")
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CommentsAdapter()
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.isNestedScrollingEnabled=true
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.comments.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()){
                        adapter.setItems(ArrayList(it.data))
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.charactersRv.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.charactersRv.visibility = View.GONE
                }
            }
        })
    }
}
