package com.example.confession.Adaptor

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.confession.Post
import com.example.confession.PostDetailActivity
import com.example.confession.R
import javax.security.auth.Subject

class PostAdaptor(context: Context, data: List<Post>) : RecyclerView.Adapter<PostAdaptor.MyViewHolder>() {

    var context : Context
    var data : List<Post>

    init  {
        this.context = context
        this.data = data
    }


    //creates view for the what the post looks like in the forum
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdaptor.MyViewHolder {
        var posts = LayoutInflater.from(context).inflate(R.layout.list_posting, parent, false)
        return MyViewHolder(posts)
    }

    // binds value to the view
    override fun onBindViewHolder(holder: PostAdaptor.MyViewHolder, position: Int) {
        holder.title.setText(data.get(position).getTitle())
        holder.subject.setText(data.get(position).getSubject())
        holder.description.setText(data.get(position).getDescription())
    }

    //getter for the amount of post
    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.Title_text)
        var subject: TextView = itemView.findViewById(R.id.Subject_text)
        var description : TextView = itemView.findViewById(R.id.Desc_text)

        init {
            //checks if post is clicked then sends data to postdetailactivity
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, PostDetailActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                var position = adapterPosition

                intent.putExtra("title", data.get(position).getTitle())
                intent.putExtra("subject", data.get(position).getSubject())
                intent.putExtra("description", data.get(position).getDescription())
                intent.putExtra("postKey", data.get(position).getKey())


                context.startActivity(intent)



            }
        }
    }

}