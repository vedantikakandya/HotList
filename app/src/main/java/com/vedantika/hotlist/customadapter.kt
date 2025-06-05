package com.vedantika.hotlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.vedantika.hotlist.taskitem

class customadapter(context: Context, val items: MutableList<taskitem>):
    ArrayAdapter<taskitem>(context,0,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        var item = items[position]
        val tasktext = view.findViewById<EditText>(R.id.task)
        val checkbox = view.findViewById<CheckBox>(R.id.checkBox)
        val delete = view.findViewById<ImageButton>(R.id.delete)
        tasktext.setText(item.task)
        checkbox.isChecked = item.ischecked
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.ischecked = isChecked
            if (isChecked) {
                tasktext.setTextColor(ContextCompat.getColor(context, R.color.gray))
                tasktext.paint.isStrikeThruText = true
                Toast.makeText(context, "Task Completed", Toast.LENGTH_SHORT).show()
            }else{
                tasktext.setTextColor(ContextCompat.getColor(context, R.color.black))
                tasktext.paint.isStrikeThruText = false
            }
            taskstore().writedata(ArrayList(items), context)
        }
        tasktext.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                item.task = tasktext.text.toString()
                taskstore().writedata(ArrayList(items), context)
            }
        }



        delete.setOnClickListener {
            items.removeAt(position)
            taskstore().writedata(ArrayList(items), context)
            notifyDataSetChanged()
            Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }

            return view
        }
    }
