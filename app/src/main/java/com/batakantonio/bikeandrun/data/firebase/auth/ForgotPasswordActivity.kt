package com.batakantonio.bikeandrun.data.firebase.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.batakantonio.bikeandrun.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater).apply {

            supportActionBar?.hide()

            btnResetPassword.setOnClickListener {
                val userEmail = etEnterEmail.text.toString()
                auth.sendPasswordResetEmail(userEmail).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "We sent a password reset mail to your email address",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }
            imgBack.setOnClickListener {
                val intent = Intent(this@ForgotPasswordActivity, SignInActivity::class.java)
                startActivity(intent)
            }
            setContentView(root)
        }
    }
}
