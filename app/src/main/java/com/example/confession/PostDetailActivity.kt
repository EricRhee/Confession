package com.example.confession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.confession.Adaptor.CommentAdaptor
import com.example.confession.Adaptor.PostAdaptor
import com.example.confession.databinding.ActivityPostDetailBinding
import com.example.confession.databinding.IntroScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PostDetailActivity : AppCompatActivity() {
    private lateinit var title : TextView
    private lateinit var subject: TextView
    private lateinit var description : TextView
    private lateinit var comment : EditText
    private lateinit var addComment : Button
    private lateinit var binding : ActivityPostDetailBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseUser : FirebaseUser
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var PostKey : String
    private lateinit var commentRV : RecyclerView
    private lateinit var commentAdaptor : CommentAdaptor
    lateinit var commentList : List<Comment>




    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("PostDetail", "YABA")
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        commentRV = binding.commentRecyclerview
        Log.v("PostDetail", "DABA")
        title = binding.postdetailtitleTextview
        subject = binding.postdetailsubjectTextview
        description = binding.postdetaildescrTextview
        comment = binding.commentPlaintext
        addComment = binding.addcommentButton


        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!
        firebaseDatabase = FirebaseDatabase.getInstance()



        var passedTitle = intent.extras?.getString("title")
        var passedSubject = intent.extras?.getString("subject")
        var passedDescription = intent.extras?.getString("description")

        PostKey = intent.extras?.getString("postKey").toString()

        title.setText(passedTitle)
        subject.setText(passedSubject)
        description.setText(passedDescription)

        commentInitialization()

        Log.v("PostDetail", "Third")

        binding.backButton.setOnClickListener{
            var intent = Intent(this, ForumActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            onDestroyView()
            finish()
        }
        addComment.setOnClickListener{
            var commentref = firebaseDatabase.getReference("Comment").child(PostKey).push()
            var context = binding.commentPlaintext.text.toString()
            var comment = Comment(context)

            commentref.setValue(comment).addOnSuccessListener {
                Toast.makeText(this, "Comment has been Added", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun commentInitialization() {
        commentRV.setLayoutManager(LinearLayoutManager(this))
        var commentref = firebaseDatabase.getReference("Comment").child(PostKey)
        commentref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                commentList = mutableListOf()
                for (snap in snapshot.children) {

                    var comment = snap.getValue(Comment::class.java)

                    comment?.let {
                        (commentList as MutableList<Comment>).add(it)
                    }

                }
                commentAdaptor = CommentAdaptor(applicationContext, commentList)
                commentRV.adapter = (commentAdaptor)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    fun onDestroyView() {
        commentRV.adapter = null
        commentRV.layoutManager = null
    }
}