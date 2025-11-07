package com.batakantonio.bikeandrun.data.firebase.users

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batakantonio.bikeandrun.data.firebase.FirebaseMainActivity
import com.batakantonio.bikeandrun.databinding.UsersItemBinding

class UsersAdapter(
    var context: FirebaseMainActivity,
    var userList: ArrayList<Users>
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(val bindingAdapter: UsersItemBinding) :
        RecyclerView.ViewHolder(bindingAdapter.root)

    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {

        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UsersViewHolder,
        position: Int
    ) {
        holder.bindingAdapter.tvName.text = userList[position].userName
        holder.bindingAdapter.tvAge.text = userList[position].userAge.toString()
        holder.bindingAdapter.tvEmail.text = userList[position].userEmail

        holder.bindingAdapter.linearLayout.setOnClickListener {

            val intent = Intent(context, UpdateUserActivity::class.java)
            intent.putExtra("id", userList[position].userId)
            intent.putExtra("name", userList[position].userName)
            intent.putExtra("age", userList[position].userAge)
            intent.putExtra("email", userList[position].userEmail)
            context.startActivity(intent)
        }
    }

        // Method will detect the position of the element to be deleted
        fun getUserId(position: Int) : String {
            return userList[position].userId
        }
    }

