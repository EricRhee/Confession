package com.example.confession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.confession.databinding.ActivityPopupBinding
import com.example.confession.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class PopupActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPopupBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var user : FirebaseUser



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth.currentUser!!
        binding.button.setOnClickListener(View.OnClickListener() {
            val title = binding.titleTextfield.text.toString()
            val subject = binding.subjectTextfield.text.toString()
            val main = binding.mainTextfield.text.toString()
            val userId = user.uid

            //checks if fields are not empty and if so initializes the addPost method
            if (title.isNotEmpty() && subject.isNotEmpty() && main.isNotEmpty()) {
                val post = Post(title, subject, main, userId)

                addPost(post)
            } else {
                Toast.makeText(this, "Please Verify All Input Fields", Toast.LENGTH_SHORT).show()
            }
        })
        //back button
        binding.floatingActionButton2.setOnClickListener{
            finish()
        }

    }

    private fun addPost(post: Post) {
        var database = FirebaseDatabase.getInstance()
        var ref = database.getReference("Posts").push()

        //sets key to the post so we can differentiate
        var key : String? = ref.key
        if (key != null) {
            post.setKey(key)
        }

        ref.setValue(post).addOnSuccessListener {
            Toast.makeText(this, "Confession Added Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}