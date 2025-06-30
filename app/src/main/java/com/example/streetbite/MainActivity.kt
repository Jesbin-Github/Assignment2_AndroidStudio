package com.example.streetbite

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge



import com.example.streetbite.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) { // setting up all the button and screen part using onCreate
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // initial values for the created binding object:
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // navigate to the first sign up page:
        binding.signupbtn1.setOnClickListener {

            // send an order to the sign up page to open
            // to send intent : we create the intent then send

            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)

        }

        // navigate to the first login page:
        binding.loginbtn1.setOnClickListener {

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }

        // navigate to the second sign up page:
        binding.signupbtn2.setOnClickListener {

            val intent = Intent(this,Signup2Activity::class.java)
            startActivity(intent)

        }

        // navigate to the second login page:
        binding.loginbtn2.setOnClickListener {

            val intent = Intent(this,Login2Activity::class.java)
            startActivity(intent)

        }

        binding.updatebtn.setOnClickListener {


            // intent to go to url
            // If we want our code to access any information
            // It will access the information in the from : Uri
            // We want to access url ( link) ,
            // we will give the link in the form of uri

            val updatesUrl = "https://github.blog/changelog/"
            // create the order (intent)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updatesUrl))
            startActivity(intent)
        }
    }
}
