package com.example.slambook

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Nav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nav)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the ImageView for the GIF
        val gifImageView = findViewById<ImageView>(R.id.nextButton)
        Glide.with(this)
            .asGif()
            .load(R.drawable.next)
            .into(gifImageView)

        // Set an OnClickListener on the GIF ImageView
        gifImageView.setOnClickListener {
            // Navigate to HomeActivity
            val intent = Intent(this, Nav2::class.java)
            startActivity(intent)
        }

        val gifImageBook = findViewById<ImageView>(R.id.book)
        Glide.with(this)
            .asGif()
            .load(R.drawable.book)
            .into(gifImageBook)
    }
}