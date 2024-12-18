package com.example.slambook

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class create : AppCompatActivity() {
    private lateinit var countries: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextFavoritePet = findViewById<EditText>(R.id.editTextFavoritePet)
        val textViewAge = findViewById<TextView>(R.id.textViewAge)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        // Gender Spinner setup
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val genders = arrayOf("Select Gender", "Male", "Female", "Other")
        val adapterGender = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapterGender

        // Marital Status Spinner setup
        val spinnerMaritalStatus = findViewById<Spinner>(R.id.spinnerMaritalStatus)
        val maritalStatuses = arrayOf("Select Marital Status", "Single", "Married", "Divorced", "Widowed")
        val adapterMaritalStatus = ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalStatuses)
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMaritalStatus.adapter = adapterMaritalStatus

        // Date of Birth Spinners setup
        val spinnerMonth = findViewById<Spinner>(R.id.spinnerMonth)
        val spinnerDay = findViewById<Spinner>(R.id.spinnerDay)
        val spinnerYear = findViewById<Spinner>(R.id.spinnerYear)

        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val adapterMonth = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMonth.adapter = adapterMonth

        val startYear = 1950
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = (startYear..currentYear).map { it.toString() }.toTypedArray()
        val adapterYear = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerYear.adapter = adapterYear

        // Country Spinner setup
        val spinnerPlanToVisitCountry = findViewById<Spinner>(R.id.spinnerPlanToVisitCountry)
        countries = listOf(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
            "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
            "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", " Bhutan",
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
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
            "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen",
            "Zambia", "Zimbabwe" /* ... other countries ... */
        )
        val adapterCountry = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPlanToVisitCountry.adapter = adapterCountry

        // Pet Spinner setup
        val spinnerPet = findViewById<Spinner>(R.id.spinnerPet)
        val pets = arrayOf("Select Pet", "Dog", "Cat", "Rabbit", "Bird", "Other")
        val adapterPet = ArrayAdapter(this, android.R.layout.simple_spinner_item, pets)
        adapterPet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPet.adapter = adapterPet

        // Update days in spinnerDay when month or year is selected
        val updateDays = {
            val month = spinnerMonth.selectedItemPosition + 1
            val year = spinnerYear.selectedItem.toString().toIntOrNull()
            val daysInMonth = getDaysInMonth(month, year)
            val days = (1..daysInMonth). map { it.toString() }.toTypedArray()
            val adapterDay = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
            adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDay.adapter = adapterDay
            // Reset day selection
            spinnerDay.setSelection(0)
        }

        // Set listeners for month and year spinners
        spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View,
                position: Int,
                id: Long
            ) {
                updateDays()
                calculateAndDisplayAge(textViewAge, spinnerDay, spinnerMonth, spinnerYear)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View,
                position: Int,
                id: Long
            ) {
                updateDays()
                calculateAndDisplayAge(textViewAge, spinnerDay, spinnerMonth, spinnerYear)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View,
                position: Int,
                id: Long
            ) {
                calculateAndDisplayAge(textViewAge, spinnerDay, spinnerMonth, spinnerYear)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Submit Button Click Listener
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val favoritePet = editTextFavoritePet.text.toString().trim()
            val selectedGender = spinnerGender.selectedItem.toString()
            val selectedMaritalStatus = spinnerMaritalStatus.selectedItem.toString()
            val selectedCountry = spinnerPlanToVisitCountry.selectedItem.toString()
            val day = spinnerDay.selectedItem.toString().toIntOrNull()
            val month = spinnerMonth.selectedItemPosition + 1
            val year = spinnerYear.selectedItem.toString().toIntOrNull()

            if (!isNameValid(name)) {
                Toast.makeText(this, "Name should not contain numbers", Toast.LENGTH_SHORT).show()
            } else if (name.isNotEmpty() && day != null && month != null && year != null &&
                selectedMaritalStatus != "Select Marital Status" &&
                selectedGender != "Select Gender" &&
                selectedCountry != "Where You came from") {
                val age = calculateAge(day, month, year)
                if (age >= 5) {
                    // Create a new user
                    val newUser  = User(
                        name,
                        age,
                        selectedGender,
                        selectedMaritalStatus,
                        selectedCountry,
                        favoritePet
                    )

                    // Add the new user to the list in Nav2
                    Nav2.users.add(newUser )

                    // Navigate back to Nav2
                    val intent = Intent(this, Nav2::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "You must be at least 5 years old", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }}
    private fun isNameValid(name: String): Boolean {
        return name.all { it.isLetter() || it.isWhitespace() }
    }

    private fun getDaysInMonth(month: Int, year: Int?): Int {
        return if (month == 2) {
            if (year != null && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) 29 else 28
        } else if (month in listOf(4, 6, 9, 11)) 30 else 31
    }

    private fun calculateAge(day: Int, month: Int, year: Int): Int {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        var age = currentYear - year
        if (month > currentMonth || (month == currentMonth && day > currentDay)) {
            age--
        }
        return age
    }

    private fun calculateAndDisplayAge(textViewAge: TextView, spinnerDay: Spinner, spinnerMonth: Spinner, spinnerYear: Spinner) {
        val day = spinnerDay.selectedItem.toString().toIntOrNull()
        val month = spinnerMonth.selectedItemPosition + 1
        val year = spinnerYear.selectedItem.toString().toIntOrNull()

        if (day != null && month != null && year != null) {
            val age = calculateAge(day, month, year)
            textViewAge.text = "Age: $age"
        } else {
            textViewAge.text = "Age: N/A"
        }
    }
}