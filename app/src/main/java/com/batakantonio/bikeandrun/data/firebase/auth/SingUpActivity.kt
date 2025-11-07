package com.batakantonio.bikeandrun.data.firebase.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.batakantonio.bikeandrun.MainActivity
import com.batakantonio.bikeandrun.data.firebase.FirebaseMainActivity
import com.batakantonio.bikeandrun.databinding.ActivitySingUpBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater).apply {
            supportActionBar?.hide()
            setContentView(root)

        }
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnSingUp.setOnClickListener {
                val userEmail = etEmail.text.toString()
                val userPassword = etPassword.text.toString()

                if (validateEmail() && validatePassword()) {
                    signUpWithEmailAndPassword(userEmail, userPassword)
                }
            }
            tvSingUp.setOnClickListener {
                val intent = Intent(this@SingUpActivity, FirebaseMainActivity::class.java)
                startActivity(intent)
            }
            imgBack.setOnClickListener {
                val intent = Intent(this@SingUpActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun signUpWithEmailAndPassword(userEmail: String, userPassword: String) {
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Your account has been created",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
    }

    fun validatePassword(): Boolean {
        binding.apply {
            return when {
                etPassword.text.toString().length < 8 -> {
                    etPassword.error = "Password length must be at least 8 characters"
                    false
                }

                etPassword.text.toString() != etConfirmPassword.text.toString() -> {
                    etPassword.error = "Passwords are not the same"
                    etConfirmPassword.error = "Passwords are not the same"
                    false
                }

                else -> {
                    etPassword.error = null
                    etConfirmPassword.error = null
                    true
                }
            }
        }
    }

    fun validateEmail(): Boolean {
        binding.apply {
            return when {
                etEmail.text.toString().length < 2 -> {
                    etEmail.error = "Email must contains at least 2 characters"
                    false
                }

                !etEmail.text.toString().isValidEmail() -> {
                    etEmail.error = "Email must contains '@'"
                    false
                }

                else -> {
                    etEmail.error = null
                    true
                }
            }
        }
    }
    private fun String.isValidEmail(): Boolean {
        val emailPattern =
            "^[a-zA-Z0-9#~!$&'()*+,;=:.\"<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
        val pattern = Pattern.compile(emailPattern)
        return pattern.matcher(this).matches()
    }
}