package com.batakantonio.bikeandrun.data.firebase.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.batakantonio.bikeandrun.databinding.ActivityUpdateUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater).apply {

            supportActionBar?.title = "Update User"

            getAndSetData()

            btnUpdateUser.setOnClickListener {
                updateData()
            }
            setContentView(root)
        }
    }

    fun getAndSetData() {
        binding.apply {
            val name = intent.getStringExtra("name")
            val age = intent.getIntExtra("age", 0).toString()
            val email = intent.getStringExtra("email")

            etUpdateName.setText(name)
            etUpdateAge.setText(age)
            etUpdateEmail.setText(email)
        }
    }

    private fun updateData() {
        binding.apply {
            val updateName = etUpdateName.text.toString()
            val updateEmail = etUpdateEmail.text.toString()
            val updateAge = etUpdateAge.text.toString().toInt()
            val userId = intent.getStringExtra("id").toString()

            val userMap = mutableMapOf<String, Any>()
            userMap["userId"] = userId
            userMap["userName"] = updateName
            userMap["userAge"] = updateAge
            userMap["userEmail"] = updateEmail

            myReference.child(userId).updateChildren(userMap).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "The user has been updated successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }
}