package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.lostfound.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



class SettingsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val logout = findViewById<Button>(R.id.logoutbutton)
        logout.setOnClickListener{
            auth.signOut()
        Toast.makeText(baseContext,"Logged out", Toast.LENGTH_SHORT)
        startActivity(intent)
        }

        val user = auth.currentUser
        if (user!=null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener {document->
                    if (document !=null){
                        val name1 = user.displayName
                        val name2 = user.displayName
                        val phone = document.get("phone") as String

                    }
                }
                .addOnFailureListener { exception->
                    Toast.makeText(baseContext, "Failed to retrieve user data", Toast.LENGTH_SHORT).show()
                }

        }
    }
}