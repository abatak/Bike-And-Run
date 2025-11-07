package com.batakantonio.bikeandrun.ui.running

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batakantonio.bikeandrun.data.database.Record
import com.batakantonio.bikeandrun.databinding.ItemRecordBinding

class RecordAdapter(
    private val listener: RecordItemClickListener
) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    private var records: List<Record> = listOf()

    override fun getItemCount(): Int = records.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemRecordBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(records[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRecords(records: List<Record>) {
        this.records = records.sortedBy { record ->
            record.isChecked
        }
        // Notify any registered observers that the data set has changed
        // Reusing 'ViewHolder'
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.apply {
                // Delete record on long pressing on listener
                this.root.setOnLongClickListener {
                    listener.onRecordDeleted(record)
                    true
                }
                cbRecord.isChecked = record.isChecked
                if (record.isChecked) {
                    tvRecordName.paintFlags = tvRecordName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    tvTime.paintFlags = tvRecordName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    tvRecordName.paintFlags = 0
                    tvTime.paintFlags = 0
                }
                tvRecordName.text = record.title
                tvTime.text = record.time
                cbRecord.setOnClickListener {
                    val updatedRecord = record.copy(isChecked = cbRecord.isChecked)
                    listener.onRecordUpdated(updatedRecord)
                }
            }
        }
    }
}

interface RecordItemClickListener {
    fun onRecordUpdated(record: Record)

    fun onRecordDeleted(record: Record)
}
