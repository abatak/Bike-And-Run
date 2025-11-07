package com.batakantonio.bikeandrun.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.batakantonio.bikeandrun.R
import com.batakantonio.bikeandrun.data.database.Record
import com.batakantonio.bikeandrun.data.model.EditRecordsViewModel
import com.batakantonio.bikeandrun.databinding.ActivityEditRecordsBinding
import com.batakantonio.bikeandrun.databinding.DialogEditRecordBinding
import com.batakantonio.bikeandrun.ui.running.RecordAdapter
import com.batakantonio.bikeandrun.ui.running.RecordItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditRecordsActivity : AppCompatActivity(), RecordItemClickListener {

    private val viewModel by viewModel<EditRecordsViewModel>()
    private lateinit var binding: ActivityEditRecordsBinding

    private val adapter = RecordAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordsBinding.inflate(layoutInflater).apply {
            fabAddRecords.setOnClickListener {
                showAddDialogRecord()
            }

            supportActionBar?.hide()

            // Handle manual menu item
            recordsToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.clear_all_records -> {
                        showMessageDialog()
                        true
                    }

                    else -> false
                }
            }

            recordsToolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            recyclerViewRecords.adapter = adapter
            fetchAllRecords()
            setContentView(root)
        }
    }

    private fun showMessageDialog() {
        AlertDialog.Builder(this)
            .setTitle("Clear All Records")
            .setMessage("Are you sure you want delete all records?")
            .setPositiveButton("Yes") { dialog, _ ->
                lifecycleScope.launch {
                    viewModel.deleteAllRecords()
                }
                // Refreshing screen after deleting records
                fetchAllRecords()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }.show()

    }


    fun showAddDialogRecord() {
        DialogEditRecordBinding.inflate(layoutInflater).apply {
            val dialog = BottomSheetDialog(this@EditRecordsActivity)
            dialog.setContentView(root)

            saveBtn.isEnabled = false

            // If 'etRecordName' is null or empty can't save the record
            etRecordName.addTextChangedListener { input ->
                saveBtn.isEnabled = !input.isNullOrEmpty()
            }

            etTime.addTextChangedListener { input ->
                saveBtn.isEnabled = !input.isNullOrEmpty()

            }

            saveBtn.setOnClickListener {
                viewModel.createRecord(
                    title = etRecordName.text.toString(),
                    time = etTime.text.toString()
                )
                dialog.dismiss()

            }
            dialog.show()
        }
    }

    private fun fetchAllRecords() {
        // pre-configured scope that will run on the Main thread
        // and we're allowed to update our UI ( start and pause our function)
        lifecycleScope.launch {
            viewModel.fetchRecords().collectLatest { records ->
                adapter.setRecords(records)
            }
        }
    }

    override fun onRecordUpdated(record: Record) {
        viewModel.updateRecord(record)
    }

    override fun onRecordDeleted(record: Record) {
        viewModel.deleteRecord(record)
    }
}
