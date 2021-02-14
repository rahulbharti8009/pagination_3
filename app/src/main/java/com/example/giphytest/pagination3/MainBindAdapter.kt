package com.example.giphytest.pagination3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.giphytest.R
import com.example.giphytest.pagination3.enity.Children
import com.example.giphytest.pagination3.enity.RedditPost

class MainBindAdapter(var view: View) : RecyclerView.ViewHolder(view) {

    var textViewName: TextView = view.findViewById(R.id.textViewName)
    var textViewEmail: TextView = view.findViewById(R.id.textViewEmail)

    init {
        view.setOnClickListener{

        }
    }

    companion object {
        fun create(parent: ViewGroup): MainBindAdapter {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pagging_list_item, parent, false)
            return MainBindAdapter(view)
        }
    }

    fun bind(children: RedditPost) {
        textViewName.text = children.name
        textViewEmail.text = children.title
    }

    fun updateScore(children: RedditPost){
        textViewEmail.text = children.title
    }
}