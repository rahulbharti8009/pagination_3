package com.example.giphytest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphytest.R
import com.example.giphytest.databinding.CustomGiphyItemBinding
import com.example.giphytest.entity.response.Data


class GiphyAdapter(var context: Context, var mList: MutableList<Data>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var binding: CustomGiphyItemBinding

//    fun setData(mListsecond: MutableList<Data>?){
//        mList!!.clear()
//        mList!!.addAll(mListsecond!!)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.custom_giphy_item,
            parent,
            false
        );
        return VideoItem(binding.root, context)
    }

    class VideoItem(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        override fun onClick(v: View?) {
            when (v?.id) {

                else -> {
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context)
            .load(mList!![position].images.original.url)
            .into(binding.ivThumb)
        binding.tvText.text = mList!![position].type
    }

    override fun getItemCount(): Int {
        return if(mList != null) mList!!.size else 0
    }
}