package com.example.giphytest.pagination3

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.giphytest.pagination3.enity.Children
import com.example.giphytest.pagination3.enity.RedditPost

class MainListAdapter : PagingDataAdapter<RedditPost, MainBindAdapter>(DataDifferntiator) {

    override fun onBindViewHolder(holder: MainBindAdapter, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBindAdapter {
        return MainBindAdapter.create(parent)
    }

    override fun onBindViewHolder(holder: MainBindAdapter, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
                holder.updateScore(item!!)
        } else {
            onBindViewHolder(holder, position)
        }
    }
    object DataDifferntiator : DiffUtil.ItemCallback<RedditPost>() {
        override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
            return oldItem == newItem
        }
    }
}