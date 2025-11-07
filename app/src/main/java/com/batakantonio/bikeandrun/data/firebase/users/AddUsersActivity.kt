package com.batakantonio.bikeandrun.data.firebase.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.batakantonio.bikeandrun.data.firebase.FirebaseMainActivity
import com.batakantonio.bikeandrun.databinding.ActivityAddUsersBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUsersBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUsersBinding.inflate(layoutInflater).apply {

            supportActionBar?.hide()

            btnAddUser.setOnClickListener {
                addUserToDatabase()
            }

            firebaseAddUsersToolbar.setNavigationOnClickListener {
                val intent = Intent(this@AddUsersActivity, FirebaseMainActivity::class.java)
                startActivity(intent)
            }
            setContentView(root)
        }
    }

    private fun addUserToDatabase() {
        binding.apply {
            val name: String = etName.text.toString()
            val age: Int = etAge.text.toString().toInt()
            val email: String = etEmail.text.toString()

            val id: String = myReference.push().key.toString()
            val user = Users(id, name, age, email)

            myReference.child(id).setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "The new user added to database!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        task.exception.toString(),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}


