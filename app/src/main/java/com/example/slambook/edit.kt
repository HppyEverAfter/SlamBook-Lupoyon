package com.example.slambook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class edit : AppCompatActivity() {
    private var userIndex: Int = -1
    private val countries = listOf(
        "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
        "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
        "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
        "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria",
        "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada",
        "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros",
        "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus",
        "Czechia (Czech Republic)", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
        "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
        "Eswatini (Swaziland)", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia",
        "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
        "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India",
        "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
        "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
        "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania",
        "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
        "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova",
        "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)",
        "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger",
        "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau",
        "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
        "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis",
        "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
        "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
        "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
        "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname",
        "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
        "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
        "Tuvalu", "Uganda", " Ukraine", "United Arab Emirates", "United Kingdom", "United States",
        "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen",
        "Zambia", "Zimbabwe" // Add more countries as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextFavoritePet = findViewById<EditText>(R.id.editTextFavoritePet)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val spinnerMaritalStatus = findViewById<Spinner>(R.id.spinnerMaritalStatus)
        val spinnerCountry = findViewById<Spinner>(R.id.spinnerCountry)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)

        // Set up adapters for spinners
        setupGenderSpinner(spinnerGender)
        setupMaritalStatusSpinner(spinnerMaritalStatus)
        setupCountrySpinner(spinnerCountry)

        // Retrieve user data from the intent
        userIndex = intent.getIntExtra("USER_INDEX", -1)
        val userName = intent.getStringExtra("USER_NAME") ?: ""
        val userFavoritePet = intent.getStringExtra("USER_FAVORITE_PET") ?: ""
        val userGender = intent.getStringExtra("USER_GENDER") ?: "Not Specified"
        val userMaritalStatus = intent.getStringExtra("USER_MARITAL_STATUS") ?: "Not Specified"
        val userCountry = intent.getStringExtra("USER_COUNTRY") ?: "Not Specified"
        val userAge = intent.getIntExtra("USER_AGE", 0) // Retrieve age

        // Populate fields with existing user data
        editTextName.setText(userName)
        editTextFavoritePet.setText(userFavoritePet)

        // Set the selected values for spinners based on user data
        spinnerGender.setSelection(getIndex(spinnerGender, userGender))
        spinnerMaritalStatus.setSelection(getIndex(spinnerMaritalStatus, userMaritalStatus))
        spinnerCountry.setSelection(getIndex(spinnerCountry, userCountry))

        // Set up the save button click listener
        buttonSave.setOnClickListener {
            val updatedName = editTextName.text.toString().trim()
            val updatedFavoritePet = editTextFavoritePet.text.toString().trim()
            val updatedGender = spinnerGender.selectedItem.toString()
            val updatedMaritalStatus = spinnerMaritalStatus.selectedItem.toString()
            val updatedCountry = spinnerCountry.selectedItem.toString()

            // Check if the name is not empty
            if (updatedName.isNotEmpty()) {
                // Check if userIndex is valid
                if (userIndex != -1 && userIndex < Nav2.users.size) {
                    val updatedUser  = User(
                        updatedName,
                        userAge, // Keep the age as it is
                        updatedGender,
                        updatedMaritalStatus,
                        updatedCountry,
                        updatedFavoritePet // Favorite pet is optional now
                    )
                    Nav2.users[userIndex] = updatedUser
                    Toast.makeText(this, "User  information updated successfully", Toast.LENGTH_SHORT).show()

                    // Navigate back to Nav2
                    val intent = Intent(this, Nav2::class.java)
                    startActivity(intent)
                    finish() // Close the current activity
                } else {
                    Log.e("EditActivity", "Invalid user index: $userIndex")
                    Toast.makeText(this, "Error updating user information", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in the name field", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up the cancel button click listener
        buttonCancel.setOnClickListener {
            // Navigate back to Nav2 without saving
            val intent = Intent(this, Nav2::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
    }

    private fun setupGenderSpinner(spinner: Spinner) {
        val genders = arrayOf("Select Gender", "Male", "Female", "Other")
        val adapterGender = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterGender
    }

    private fun setupMaritalStatusSpinner(spinner: Spinner) {
        val maritalStatuses = arrayOf("Select Marital Status", "Single", "Married", "Divorced", "Widowed")
        val adapterMaritalStatus = ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalStatuses)
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterMaritalStatus
    }

    private fun setupCountrySpinner(spinner: Spinner) {
        val adapterCountry = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterCountry
    }

    // Helper function to get the index of a spinner item
    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString() == myString) {
                return i
            }
        }
        return 0 // Default to the first item if not found
    }
}