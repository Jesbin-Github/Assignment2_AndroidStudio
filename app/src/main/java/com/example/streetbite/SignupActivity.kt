package com.example.streetbite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.streetbite.databinding.ActivitySignUpBinding
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupbtn.setOnClickListener {
            val email = binding.editTextTextEmailAddress2.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextTextPassword3.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                saveToFirestore(email, password)
            }
        }

        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun saveToFirestore(email1: String, password1: String) {
        val user = hashMapOf(
            "email" to email1,
            "password" to password1,
            "type" to "primary"
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this,"Account Created !" , Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Signup Failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}
