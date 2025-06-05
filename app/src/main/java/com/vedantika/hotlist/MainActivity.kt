package com.vedantika.hotlist

import com.vedantika.hotlist.customadapter
import android.os.Bundle
import android.content.Context
import android.content.SharedPreferences
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vedantika.hotlist.databinding.ActivityMainBinding
import com.vedantika.hotlist.taskitem

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Hot List"
        binding.toolbar.subtitle = "what's your plan today?"

        var taskmanage=taskstore()

        var listoftask = taskmanage.readData(this)
        val adapter = customadapter(this, listoftask)
        binding.taskList.adapter = adapter

        binding.add.setOnClickListener {
            val text=binding.mentiontask.text.toString()
            if(text.isNotEmpty()) {
                val task = taskitem(text)
                listoftask.add(task)
                taskmanage.writedata(ArrayList(listoftask), this)
                adapter.notifyDataSetChanged()
                binding.mentiontask.text.clear()
                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}