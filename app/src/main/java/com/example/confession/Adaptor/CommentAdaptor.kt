package com.example.confession.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.confession.Comment
import com.example.confession.Post
import com.example.confession.R
import kotlinx.coroutines.NonDisposableHandle.parent

class CommentAdaptor(context: Context, data: List<Comment>) : RecyclerView.Adapter<CommentAdaptor.CommentViewHolder>() {

    private lateinit var context : Context
    private lateinit var data : List<Comment>


    init  {
        this.context = context
        this.data = data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var comment = LayoutInflater.from(context).inflate(R.layout.comment_posting, parent, false)
        return CommentViewHolder(comment)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.comment.setText(data.get(position).getContent())
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var comment: TextView =  itemView.findViewById(R.id.comment_textview)
    }
}