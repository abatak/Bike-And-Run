package com.batakantonio.bikeandrun.data.firebase.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.batakantonio.bikeandrun.MainActivity
import com.batakantonio.bikeandrun.data.firebase.FirebaseMainActivity
import com.batakantonio.bikeandrun.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setOnClickListeners()

        setContentView(binding.root)


    }

    private fun setOnClickListeners() {
        binding.apply {
            btnSingIn.setOnClickListener {
                val userEmail = etEmail.text.toString()
                val userPassword = etPassword.text.toString()

                if (validateEmail() && validatePassword()) {
                    signInWithEmailAndPassword(userEmail, userPassword)
                }
            }
            tvSignIn.setOnClickListener {
                val intent = Intent(this@SignInActivity, SingUpActivity::class.java)
                startActivity(intent)
            }

            tvForgotPassword.setOnClickListener {
                val intent = Intent(this@SignInActivity, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            imgBack.setOnClickListener {
                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun signInWithEmailAndPassword(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(applicationContext, "Sign in is successful", Toast.LENGTH_SHORT)
                    .show()

                val intent = Intent(this, FirebaseMainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // If sign in fails, display a message for the user
                Toast.makeText(
                    applicationContext,
                    "Error, email or password are not correct",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun validateEmail(): Boolean {

        binding.apply {
            return when {
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

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        if (user != null) {
            val intent = Intent(this@SignInActivity, FirebaseMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun String.isValidEmail(): Boolean {
        val emailPattern =
            "^[a-zA-Z0-9#~!$&'()*+,;=:.\"<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
        val pattern = Pattern.compile(emailPattern)
        return pattern.matcher(this).matches()
    }


    fun validatePassword(): Boolean {
        binding.apply {
            return when {
                etPassword.text.toString().isEmpty() || etPassword.text.toString()
                    .isBlank() -> {
                    etPassword.error = "Password cannot be empty or blank"
                    false
                }

                else -> {
                    etPassword.error = null
                    true
                }
            }
        }
    }
}

