package com.example.confession

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.confession.Adaptor.PostAdaptor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class MyPostFragment : Fragment() {
    lateinit var recycleView : RecyclerView
    lateinit var postAdaptor : PostAdaptor
    lateinit var firebaseDatabase : FirebaseDatabase
    lateinit var ref : DatabaseReference
    lateinit var postList : List<Post>
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var user : FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //allows for adding new posts into the recycleview
        var fragView = inflater.inflate(R.layout.fragment_my_post, container, false)
        recycleView = fragView.findViewById(R.id.listRV)
        recycleView.setLayoutManager(LinearLayoutManager(activity))


        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth.currentUser!!
        ref = firebaseDatabase.getReference("Posts")
        return recycleView
    }

    override fun onStart() {
        super.onStart()

        ref.addValueEventListener(object : ValueEventListener {
            // checks for new entries for posts
            override fun onDataChange(snapshot: DataSnapshot) {
                postList = mutableListOf()
                for (snap in snapshot.children) {
                    var post = snap.getValue(Post::class.java)
                    if (post != null) {
                        // condition for the tab showing only posts that this account has made
                        if (post.getUserId() == user.uid) {
                            post?.let {
                                (postList as MutableList<Post>).add(it)
                            }
                        }
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