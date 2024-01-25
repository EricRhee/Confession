package com.example.confession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.confession.databinding.ActivitySignUpBinding
import android.widget.Button
import android.widget.Toast
import com.example.confession.databinding.IntroScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        setUi()
    }

    //Make new account
    private fun setUi() {
        binding.signedupButton.setOnClickListener{
            val email = binding.signUpUsernameTextedit.text.toString()
            val password = binding.signUpPasswordTextedit.text.toString()
            val passwordconf = binding.signUpPasswordconfTextedit.text.toString()

            // checks if fields are not empty and whether password and re-enter password are the same
            if (email.isNotEmpty() && password.isNotEmpty() && passwordconf.isNotEmpty()) {
                if (password == passwordconf) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        // if valid password and email then it adds to database
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Sign Up is successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Cannot have empty fields", Toast.LENGTH_SHORT).show()
            }
        }

        //back fab allows to go back to login screen
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}