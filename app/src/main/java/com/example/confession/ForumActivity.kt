package com.example.confession

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.confession.ui.main.SectionsPagerAdapter
import com.example.confession.databinding.ActivityForumBinding
import com.google.firebase.auth.FirebaseAuth

class ForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForumBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        // allows for different fragments for each tab
        tabs.setupWithViewPager(viewPager)
        // transition to creating new entry activity
        binding.fab.setOnClickListener {
            val intent = Intent(this, PopupActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        // logout out button - goes back to login screen
        binding.button2.setOnClickListener{
            user.signOut()
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}