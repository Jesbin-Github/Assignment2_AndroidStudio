package com.example.streetbite

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.streetbite.databinding.ActivitySignUp2Binding
import com.google.firebase.firestore.FirebaseFirestore

class Signup2Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp2Binding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupbtn.setOnClickListener {
            val email = binding.editTextTextEmailAddress2.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextTextPassword3.text.toString().trim()

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            } else if (!isValidPassword(password)) {

                Toast.makeText(
                    this,
                    "Password must be 8–15 characters, contain 1 uppercase letter & 1 special character",
                    Toast.LENGTH_LONG
                ).show()

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

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{8,15}$" // verifying the password criteria
        return password.matches(Regex(passwordPattern))
    }

    private fun saveToFirestore(email: String, password: String) {
        val user = hashMapOf(
            "email" to email,
            "password" to password,
            "type" to "secondary"
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Signup Failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}
