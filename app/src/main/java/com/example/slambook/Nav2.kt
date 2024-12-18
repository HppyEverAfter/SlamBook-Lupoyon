package com.example.slambook

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Nav2 : AppCompatActivity() {
    companion object {
        val users = mutableListOf<User>() // Make users a companion object
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nav2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        val userContainer = findViewById<LinearLayout>(R.id.userContainer)
        val createButton = findViewById<Button>(R.id.create)

        // Display user names
        updateUserNames(userContainer)

        // Set an OnClickListener on the create button
        createButton.setOnClickListener {
            // Start the create activity
            val intent = Intent(this, create::class.java)
            startActivity(intent)
        }
    }

    private fun updateUserNames(userContainer: LinearLayout) {
        userContainer.removeAllViews() // Clear previous views

        for (user in users) {
            // Inflate a new TextView for each user
            val userView = LayoutInflater.from(this).inflate(R.layout.user_item, userContainer, false)
            val userNameTextView = userView.findViewById<TextView>(R.id.userNameTextView)
            userNameTextView.text = user.name

            // Set an OnClickListener to open User_Info_Activity for the selected user
            userView.setOnClickListener {
                val userIndex = users.indexOf(user)
                openUserInfoActivity(userIndex)
            }

            userContainer.addView(userView) // Add the user view to the container
        }
    }

    private fun openUserInfoActivity(userIndex: Int) {
        val user = users[userIndex]
        val intent = Intent(this, User_Info_Activity::class.java).apply {
            putExtra("USER_INDEX", userIndex) // Pass the index
            putExtra("USER_NAME", user.name)
            putExtra("USER_AGE", user.age)
            putExtra("USER_GENDER", user.gender)
            putExtra("USER_MARITAL_STATUS", user.maritalStatus)
            putExtra("USER_COUNTRY", user.country)
            putExtra("USER_FAVORITE_PET", user.favoritePet)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        // Update the user names when returning to this activity
        val userContainer = findViewById<LinearLayout>(R.id.userContainer)
        updateUserNames(userContainer)
    }
}