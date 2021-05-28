//package com.example.giphytest.activity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import androidx.activity.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.paging.LoadState
//import com.example.giphytest.R
//import com.example.giphytest.adapter.GiphyAdapter
//import com.example.giphytest.databinding.ActivityMainBinding
//import com.example.giphytest.databinding.ActivitySecondBinding
//import com.example.giphytest.pagination3.MainListAdapter
//import com.example.giphytest.pagination3.PostsLoadStateAdapter
//import com.example.giphytest.viewmodel.MyViewModel
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.flow.distinctUntilChanged
//import kotlinx.coroutines.flow.distinctUntilChangedBy
//import kotlinx.coroutines.flow.filter
//
//class Second : AppCompatActivity() {
//
//    lateinit var binding: ActivitySecondBinding
//    private val viewModel by viewModels<MyViewModel>()
//    lateinit var giphyAdapter: GiphyAdapter
//    lateinit var adapter: MainListAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySecondBinding.inflate(LayoutInflater.from(this))
//        setContentView(binding.root)
//        initAdapter()
//    }
//
//    private fun initAdapter() {
//        adapter = MainListAdapter()
//
//        binding.rvGiphy.adapter = adapter.withLoadStateHeaderAndFooter(
//            header = PostsLoadStateAdapter(adapter),
//            footer = PostsLoadStateAdapter(adapter)
//        )
//
//        lifecycleScope.launchWhenCreated {
//            adapter.loadStateFlow.collectLatest { loadStates ->
//                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            viewModel.listData.distinctUntilChanged().collectLatest {
//                adapter.submitData(it)
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            adapter.loadStateFlow
//                // Only emit when REFRESH LoadState for RemoteMediator changes.
//                .distinctUntilChangedBy { it.refresh }
//                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
//                .filter { it.refresh is LoadState.NotLoading }
//                .collectLatest { binding.rvGiphy.scrollToPosition(0) }
//        }
//    }
//}