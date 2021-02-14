package com.example.giphytest.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giphytest.adapter.GiphyAdapter
import com.example.giphytest.api.Status
import com.example.giphytest.common.BaseActivity
import com.example.giphytest.databinding.ActivityMainBinding
import com.example.giphytest.di.MyModule
import com.example.giphytest.pagination3.MainListAdapter
import com.example.giphytest.pagination3.PostsLoadStateAdapter
import com.example.giphytest.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    var tag = "MainActivity"

    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MyViewModel>()
    lateinit var giphyAdapter: GiphyAdapter
    lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initAdapter()
        initSwipeToRefresh()
    }
    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
    }

    private fun initAdapter() {
        adapter = MainListAdapter()

        binding.rvGiphy.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PostsLoadStateAdapter(adapter),
            footer = PostsLoadStateAdapter(adapter)
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.listData.distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collectLatest { binding.rvGiphy.scrollToPosition(0) }
        }
    }

    override fun initialized() {
        super.initialized()
        binding.rvGiphy.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        giphyAdapter = GiphyAdapter(this, null)
//        binding.rvGiphy.adapter = giphyAdapter

        viewModel.getData().observe(this, {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        it.data?.let {
                            Log.e(tag, "SUCCESS ${it.meta.msg}")
                            giphyAdapter = GiphyAdapter(this, it.data)
                            binding.rvGiphy.adapter = giphyAdapter
                            giphyAdapter.notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        Log.e(tag, "ERROR")
                        it?.message.let {
                            Log.e(tag, it!!)
                        }
                    }
                    Status.LOADING -> {
                        Log.e(tag, "Loading")
                    }
                }
            }
        })
    }

}