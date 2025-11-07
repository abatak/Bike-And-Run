package com.batakantonio.bikeandrun.data.firebase


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.data.firebase.auth.SignInActivity
import com.batakantonio.bikeandrun.data.firebase.auth.SingUpActivity
import com.batakantonio.bikeandrun.data.firebase.users.AddUsersActivity
import com.batakantonio.bikeandrun.data.firebase.users.Users
import com.batakantonio.bikeandrun.data.firebase.users.UsersAdapter
import com.batakantonio.bikeandrun.databinding.ActivityFirebaseMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirebaseMainBinding

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("Users")
    private val userList = ArrayList<Users>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseMainBinding.inflate(layoutInflater).apply {

            supportActionBar?.hide()
            fabAdd.setOnClickListener {
                val intent = Intent(this@FirebaseMainActivity, AddUsersActivity::class.java)
                startActivity(intent)
            }

            // Handle menu clicks
            firebaseUsersToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.delete_all -> {
                        showDialogMessage()
                        true
                    }

                    R.id.sing_out -> {
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@FirebaseMainActivity, SignInActivity::class.java)
                        startActivity(intent)
                        finish()
                        true
                    }

                    else -> false
                }
            }

            // Return to SignUpActivity
            firebaseUsersToolbar.setNavigationOnClickListener {
                val intent = Intent(this@FirebaseMainActivity, SingUpActivity::class.java)
                startActivity(intent)
            }
            setContentView(root)
        }

        // Helper class for swiping and deleting users in Recycle view
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or
                    ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // Drag & drop is not supported
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val id = usersAdapter.getUserId(viewHolder.bindingAdapterPosition)

                myReference.child(id).removeValue()

                Toast.makeText(
                    this@FirebaseMainActivity,
                    "The user has been deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }).attachToRecyclerView(binding.recycleViewFirebase)

        retrieveDataFromDatabase()
    }

    fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // First we need to clear all data from being printed more than once
                userList.clear()

                for (eachUser in snapshot.children) {

                    val user = eachUser.getValue(Users::class.java)

                    if (user != null) {
                        println("userId: ${user.userId}")
                        println("userName: ${user.userName}")
                        println("userAge: ${user.userAge}")
                        println("UserEmail: ${user.userEmail}")
                        println("*".repeat(35))

                        userList.add(user)

                    }
                    usersAdapter = UsersAdapter(this@FirebaseMainActivity, userList)
                    binding.recycleViewFirebase.adapter = usersAdapter

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showDialogMessage() {
        AlertDialog.Builder(this).apply {

            setTitle("Delete All Users")
            setMessage(
                "If you click Yes, all users will be deleted," +
                        "If you you want delete specific user, you can swipe the item you want to delete left or right"
            )
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            setPositiveButton("Yes") { dialog, _ ->

                myReference.removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        usersAdapter.notifyDataSetChanged()
                        Toast.makeText(applicationContext, "Deleted all users!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            show()
        }
    }
}