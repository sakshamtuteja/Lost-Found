package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostfound.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class SettingsActivity : AppCompatActivity() {
    private lateinit var text: TextView

    private lateinit var logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        text = findViewById(R.id.email)
        logout = findViewById(R.id.logoutbutton)
        logout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Logout Successful",Toast.LENGTH_LONG).show()
        }
    }
}