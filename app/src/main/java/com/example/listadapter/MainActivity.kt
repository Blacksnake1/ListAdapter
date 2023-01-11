package com.example.listadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadapter.databinding.ActivityMainBinding
import com.thanhviet.smartlistadapter.NoteAdapter

class MainActivity : AppCompatActivity() {
    private val noteRepository = NoteRepository()
    var dataList = noteRepository.instanceData
    private lateinit var binding : ActivityMainBinding
    private var noteAdapter = NoteAdapter() { position: Int ->
        deleteNote(position)
    }

    private fun deleteNote(position: Int) {
        dataList.removeAt(position)
        noteAdapter.notifyItemRemoved(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteAdapter.submitList(dataList)


        binding.recyclerMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        binding.btnForward.setOnClickListener {
            Toast.makeText(this, "Forward Page", Toast.LENGTH_SHORT).show()
            dataList = noteRepository.dataUpdate
            noteAdapter.submitList(dataList)
        }

        binding.btnBack.setOnClickListener {
            Toast.makeText(this, "Back Page", Toast.LENGTH_SHORT).show()
            dataList = noteRepository.instanceData
            noteAdapter.submitList(dataList)
        }
    }
}