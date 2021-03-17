package com.kotdev.postcomments.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotdev.postcomments.R
import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.app.ui.adapters.CommentsRecyclerAdapter
import com.kotdev.postcomments.app.viewmodels.CommentsViewModel
import com.kotdev.postcomments.databinding.FragmentCommentsPostBinding
import com.kotdev.postcomments.helpers.Click
import com.kotdev.postcomments.helpers.Resource
import dagger.android.support.DaggerFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommentsPostFragment : Fragment(), Click, SwipeRefreshLayout.OnRefreshListener {


    companion object {
        const val TAG = "PostsFragment"
    }

    private lateinit var binding: FragmentCommentsPostBinding

    @Inject
    lateinit var adapter: CommentsRecyclerAdapter

    private val viewModel: CommentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObserversComments()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.add).setIcon(R.drawable.ic_baseline_add_24)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.add) {
            val bottomSheetDialogFragment = BottomFragment()
            bottomSheetDialogFragment.setClick(this)
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "TAG")
            return true
        } else super.onOptionsItemSelected(item)
    }


    private fun subscribeObserversComments() {
        Log.d(TAG, "subscribeObservers")
        viewModel.observeComments(requireArguments().getInt("id")).removeObservers(viewLifecycleOwner)
        viewModel.observeComments(requireArguments().getInt("id")).observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.bar.visibility = View.VISIBLE
                        Log.d(TAG, "onChanged: LOADING")
                    }
                    is Resource.Success -> {
                        binding.bar.visibility = View.GONE
                        Log.d(TAG, "onChanged: get comments")
                        adapter.setPosts(it.data as MutableList<Comments>)
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                    is Resource.Error -> {
                        Log.d(TAG, "onChanged: ERROR" + it.message)
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter
    }

    override fun add(comment: Comments) {
       viewModel.addComment(comment).observe(viewLifecycleOwner, {
           adapter.addComment(it.data!!)
               binding.bar.visibility = View.GONE
               binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
       })
    }

    override fun onRefresh() {
        subscribeObserversComments()
    }
}


