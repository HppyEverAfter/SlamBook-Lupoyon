package com.example.slambook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class User_Info_Activity : AppCompatActivity() {
    private var current: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_info)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the data from the intent
        val userName = intent.getStringExtra("USER_NAME") ?: "Unknown User"
        val userAge = intent.getIntExtra("USER_AGE", 0)
        val userGender = intent.getStringExtra("USER_GENDER") ?: "Not Specified"
        val userMaritalStatus = intent.getStringExtra("USER_MARITAL_STATUS") ?: "Not Specified"
        val userCountry = intent.getStringExtra("USER_COUNTRY") ?: "Not Specified"
        val userFavoritePet = intent.getStringExtra("USER_FAVORITE_PET") ?: "Not Specified"

        // Create a User object
        current = User(userName, userAge, userGender, userMaritalStatus, userCountry, userFavoritePet)

        // Find the TextView to display the user's information
        val userInfoTextView = findViewById<TextView>(R.id.userInfoTextView)
        userInfoTextView.text = """
            User Name: ${current?.name}
            Age: ${current?.age}
            Gender: ${current?.gender}
            Marital Status: ${current?.maritalStatus}
            Country: ${current?.country}
            Favorite Pet: ${current?.favoritePet}
        """.trimIndent()

        // Set up Edit and Delete buttons
        val editButton = findViewById<Button>(R.id.editButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        editButton.setOnClickListener {
            // Start the create activity to edit user info
            val intent = Intent(this, edit::class.java).apply {
                putExtra("USER_NAME", current?.name)
                putExtra("USER_AGE", current?.age)
                putExtra("USER_GENDER", current?.gender)
                putExtra("USER_MARITAL_STATUS", current?.maritalStatus)
                putExtra("USER_COUNTRY", current?.country)
                putExtra("USER_FAVORITE_PET", current?.favoritePet)
                putExtra("USER_INDEX", Nav2.users.indexOf(current)) // Pass the index of the user
            }
            startActivity(intent)
        }
        deleteButton.setOnClickListener {
            // Remove the user from the list in Nav2
            if (current != null) {
                Nav2.users.remove(current)
                Toast.makeText(this, "User  deleted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User  not found", Toast.LENGTH_SHORT).show()
            }
            // Navigate back to Nav2
            val intent = Intent(this, Nav2::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
}}