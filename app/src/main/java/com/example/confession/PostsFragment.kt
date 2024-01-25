package com.example.confession

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ScrollingView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.confession.Adaptor.PostAdaptor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PostsFragment : Fragment() {
    lateinit var recycleView : RecyclerView
    lateinit var postAdaptor : PostAdaptor
    lateinit var firebaseDatabase : FirebaseDatabase
    lateinit var ref : DatabaseReference
    lateinit var postList : List<Post>

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragView = inflater.inflate(R.layout.fragment_posts, container, false)

        recycleView = fragView.findViewById(R.id.listRV)

        recycleView.setLayoutManager(LinearLayoutManager(activity))


        firebaseDatabase = FirebaseDatabase.getInstance()
        ref = firebaseDatabase.getReference("Posts")

        return recycleView


    }

    override fun onStart() {

        super.onStart()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                postList = mutableListOf()
                for (snap in snapshot.children) {

                    var post = snap.getValue(Post::class.java)

                    post?.let {
                        (postList as MutableList<Post>).add(it)
                    }

                }

                val context = activity?.applicationContext
                if (context != null) {

                    postAdaptor = PostAdaptor(context, postList)

                }

                recycleView.adapter = (postAdaptor)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        recycleView.adapter = null
        recycleView.layoutManager = null
    }

}


