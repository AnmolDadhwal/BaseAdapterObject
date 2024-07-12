package com.task.baseadapterobject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListAdapter(var list:ArrayList<Student>): BaseAdapter(){
    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(position: Int): Any {
        return list[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.activity_list_adapter,parent,false)
        val rollNo=view.findViewById<TextView>(R.id.tvRollNO)
        rollNo.setText(list[position].rollNo.toString())
        val name=view.findViewById<TextView>(R.id.tvName)
        name.setText(list[position].name.toString())
        val subject=view.findViewById<TextView>(R.id.tvSubject)
        subject.setText(list[position].subject.toString())
        return view
    }
}