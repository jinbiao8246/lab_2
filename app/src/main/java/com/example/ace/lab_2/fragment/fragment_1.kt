package com.example.ace.lab_2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ace.lab_2.R
import com.example.ace.lab_2.adpter.AddStudentAdpter
import com.example.ace.lab_2.dialog.AddStudent
import com.example.ace.lab_2.enity.Student
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.litepal.LitePal
import kotlin.concurrent.thread

/**
 * Created by ace on 2018/5/2.
 */
class fragment_1 : Fragment(){
    lateinit var recyclerView:RecyclerView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_1,null)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        recyclerView = view!!.findViewById<RecyclerView>(R.id.fragment1_recyclerview)
        initRecycleView()

        fab.onClick {
            showAddStudentDialog()
            initRecycleView()
        }

        return view
    }

    private fun showAddStudentDialog(){
        val addStudent  = AddStudent()
        addStudent.show(activity.fragmentManager)
    }

    fun initRecycleView(){

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val studentList:List<Student> =  LitePal.findAll(Student::class.java)
        val adpter = AddStudentAdpter(studentList,activity,AddStudentAdpterCallBack())
        recyclerView.adapter = adpter

    }


    inner class AddStudentAdpterCallBack:AddStudentAdpter.AddStudentAdpterCallBack{
        override fun AddStudentAdpterCallBackOnclik() {
            val layoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            val studentList:List<Student> =  LitePal.findAll(Student::class.java)
            val adpter = AddStudentAdpter(studentList,activity,AddStudentAdpterCallBack())
            recyclerView.adapter = adpter
        }

    }


}

