package com.task.baseadapterobject

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.task.baseadapterobject.databinding.ActivityMainBinding
import com.task.baseadapterobject.databinding.CustomdialogBinding

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding?=null
    var studentList = arrayListOf<Student>()
    var listAdapter=ListAdapter(studentList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.listView?.setOnItemClickListener { _, _, position,_ ->
            var dialogBinding= CustomdialogBinding.inflate(layoutInflater)
            var dialog= Dialog(this).apply{
                setContentView(dialogBinding.root)
                show()
            }
            val update="Update"
            dialogBinding.btnSubmit.setText(update)
            val upRollNo:String=studentList[position].rollNo.toString()
            dialogBinding.etRollNo.setText(upRollNo)
            val upName:String=studentList[position].name.toString()
            dialogBinding.etName.setText(upName)
            val upSubject:String=studentList[position].subject.toString()
            dialogBinding.etSubject.setText(upSubject)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.etRollNo.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etRollNo.error="Enter roll no"
                }else if (dialogBinding.etName.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etName.error="Enter Name"
                }else if (dialogBinding.etSubject.text?.toString()?.trim().isNullOrEmpty()) {
                    dialogBinding.etSubject.error = "Enter Subject"
                }else{
                    studentList.set(position,Student(dialogBinding.etRollNo.text?.toString()?.toInt(),dialogBinding.etName.text?.toString()?.trim(),dialogBinding.etSubject.text?.toString()?.trim()))
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        binding?.listView?.setOnItemLongClickListener { _, _, position, _ ->
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("DELETE")
                setMessage("Are you really want to delete the information")
                setCancelable(false)
                setPositiveButton("No"){_,_->
                    setCancelable(true)
                }
                setNegativeButton("Yes"){_,_->
                    studentList.removeAt(position)
                    listAdapter.notifyDataSetChanged()
                }
                show()
            }
            return@setOnItemLongClickListener true
        }
        binding?.listView?.adapter=listAdapter
        binding?.btnAdd?.setOnClickListener {
            var dialogBinding= CustomdialogBinding.inflate(layoutInflater)
            var dialog= Dialog(this).apply{
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.etRollNo.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etRollNo.error="Enter roll no"
                }else if (dialogBinding.etName.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etName.error="Enter Name"
                }else if (dialogBinding.etSubject.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etSubject.error="Enter Subject"
                }else{
                    studentList.add(Student(dialogBinding.etRollNo.text.toString().toInt(),dialogBinding.etName.text.toString(),dialogBinding.etSubject.text.toString()))
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }
}