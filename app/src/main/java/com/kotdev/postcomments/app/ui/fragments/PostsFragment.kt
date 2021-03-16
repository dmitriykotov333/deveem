package com.kotdev.postcomments.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotdev.postcomments.app.ui.adapters.HeaderAdapter
import com.kotdev.postcomments.app.ui.adapters.PostRecyclerAdapter
import com.kotdev.postcomments.app.viewmodels.PostsViewModel
import com.kotdev.postcomments.app.viewmodels.ViewModelProviderFactory
import com.kotdev.postcomments.databinding.FragmentPostsBinding
import com.kotdev.postcomments.helpers.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class PostsFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {


    companion object {
        const val TAG = "PostsFragment"
    }

    private lateinit var binding: FragmentPostsBinding

    @Inject
    lateinit var concatAdapter: ConcatAdapter

    @Inject
    lateinit var headerAdapter: HeaderAdapter

    @Inject
    lateinit var adapter: PostRecyclerAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: PostsViewModel by viewModels { viewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> {
                            binding.bar.visibility = VISIBLE
                            Log.d(TAG, "onChanged: LOADING")
                        }
                        is Resource.Success -> {
                            binding.bar.visibility = GONE
                            Log.d(TAG, "onChanged: get posts")
                            adapter.setPosts(it.data!!)
                        }
                        is Resource.Error -> {
                            Log.d(CommentsPostFragment.TAG, "onChanged: ERROR" + it.message)
                        }
                    }
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = concatAdapter
    }

    override fun onRefresh() {
       subscribeObservers()
        binding.swipeRefreshLayout.isRefreshing = false
    }
}


