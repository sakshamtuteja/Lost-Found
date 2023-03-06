package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codingstuff.loginandsignup.databinding.ActivitySignUpBinding
import com.example.lostfound.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener{
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val emaili = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmpass = binding.repassword.text.toString()
            if (emaili.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {
                    firebaseAuth.createUserWithEmailAndPassword(emaili, pass)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, SigninActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }  else {
                Toast.makeText(this, "Password is Not matching", Toast.LENGTH_SHORT).show()
            } else {
            Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
          }
        }
    }
}