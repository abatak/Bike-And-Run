package com.batakantonio.bikeandrun.ui.bicycle

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batakantonio.bikeandrun.data.database.BikeChallenges
import com.batakantonio.bikeandrun.databinding.ItemChallengeBinding

class ChallengeBikeAdapter(
    private val listener: ChallengeOnItemListener
) : RecyclerView.Adapter<ChallengeBikeAdapter.ViewHolder>() {

    private var data: List<BikeChallenges> = listOf<BikeChallenges>()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemChallengeBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.bind(data[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBikeChallenges(bikeChallenges: List<BikeChallenges>) {
        data = bikeChallenges
        notifyDataSetChanged()

    }

    inner class ViewHolder(val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(bikeChallenges: BikeChallenges) {
            binding.apply {

                cbChallenge.isChecked = bikeChallenges.isChecked
                if (bikeChallenges.isChecked) {
                    tvChallenge.paintFlags = tvChallenge.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    tvChallenge.paintFlags = 0
                }
                tvChallenge.text = bikeChallenges.title
                cbChallenge.setOnClickListener {
                    val updatedBikeChallenge =
                        bikeChallenges.copy(isChecked = cbChallenge.isChecked)
                    listener.onChallengeBikeUpdated( listOf(updatedBikeChallenge))

                }
            }
        }
    }
}

interface ChallengeOnItemListener {

    fun onChallengeBikeUpdated(bikeChallenges: List<BikeChallenges>)

}

