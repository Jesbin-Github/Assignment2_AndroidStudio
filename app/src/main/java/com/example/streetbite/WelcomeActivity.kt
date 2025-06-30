package com.example.streetbite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.streetbite.databinding.ActivityLoginBinding
import com.example.streetbite.databinding.ActivityWelcomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class WelcomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }



}