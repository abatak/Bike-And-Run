package com.batakantonio.bikeandrun.ui.running

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batakantonio.bikeandrun.data.database.RunningChallenges
import com.batakantonio.bikeandrun.databinding.ItemChallengeBinding
import com.batakantonio.bikeandrun.ui.running.ChallengesRunningAdapter.ViewHolder

class ChallengesRunningAdapter(
    private val listener: ChallengeRunningOnItemListener
) : RecyclerView.Adapter<ViewHolder>() {

    private var data: List<RunningChallenges> = listOf()


    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemChallengeBinding.inflate(layoutInflater, parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setRunningChallenges(runningChallenges: List<RunningChallenges>) {
        data = runningChallenges
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemChallengeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(runningChallenges: RunningChallenges) {
           binding.apply {

               cbChallenge.isChecked = runningChallenges.isChecked
               if (cbChallenge.isChecked) {
                   tvChallenge.paintFlags = tvChallenge.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
               } else {
                   tvChallenge.paintFlags = 0
               }
               tvChallenge.text = runningChallenges.title
               cbChallenge.setOnClickListener {
                   val updatedListener =
                    runningChallenges.copy(isChecked = cbChallenge.isChecked)
                   listener.onChallengeRunningUpdated( listOf(updatedListener))
               }
           }
        }
    }

    interface ChallengeRunningOnItemListener {

        fun onChallengeRunningUpdated(runningChallenges: List<RunningChallenges>)

    }
}