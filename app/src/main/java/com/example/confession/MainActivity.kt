package com.example.confession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.confession.databinding.IntroScreenBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : IntroScreenBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        setUI()
    }

    private fun setUI() {
        binding.signinButton.setOnClickListener{
            val email = binding.usernamePlaintext.text.toString()
            val password = binding.passwordPlaintext.text.toString()

            // checks if input password and email are not empty and if so, it checks the system if it is valid login
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ForumActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "You have entered an invalid username or password", Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Cannot have empty fields", Toast.LENGTH_SHORT).show()
            }
        }

        //transition to SignUpActivity
        binding.signupText.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}