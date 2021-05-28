package com.example.giphytest.pagination3

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.giphytest.entity.Data
import com.example.giphytest.entity.MtResponseEntity
import com.example.giphytest.pagination3.enity.Children
import com.example.giphytest.pagination3.enity.RedditPost
import com.example.giphytest.utils.debugLog

class MainListAdapter : PagingDataAdapter<RedditPost, MainBindAdapter>(DataDifferntiator) {

    override fun onBindViewHolder(holder: MainBindAdapter, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBindAdapter {
        return MainBindAdapter.create(parent)
    }

    override fun onBindViewHolder(holder: MainBindAdapter, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            debugLog(tag = "rahul", message = "updateScore")
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
//
//class MainListAdapter : PagingDataAdapter<Data, MainBindAdapter>(DataDifferntiator) {
//
//    override fun onBindViewHolder(holder: MainBindAdapter, position: Int) {
//        holder.bind(getItem(position)!!)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBindAdapter {
//        return MainBindAdapter.create(parent)
//    }
//
//    override fun onBindViewHolder(holder: MainBindAdapter, position: Int, payloads: MutableList<Any>) {
//        if (payloads.isNotEmpty()) {
//            val item = getItem(position)
//                holder.updateScore(item!!)
//        } else {
//            onBindViewHolder(holder, position)
//        }
//    }
//    object DataDifferntiator : DiffUtil.ItemCallback<Data>() {
//        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
//            return oldItem.id == newItem.id
//        }
//        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
//            return oldItem == newItem
//        }
//    }
//}