package com.example.ace.lab_2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ace.lab_2.R
import com.example.ace.lab_2.adpter.AddStudentAdpter
import com.example.ace.lab_2.adpter.AlterGradeAdpter
import com.example.ace.lab_2.enity.Student
import org.litepal.LitePal

/**
 * Created by ace on 2018/5/9.
 */
class fragment_2: Fragment() {
    lateinit var recyclerView: RecyclerView
    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.Mytheme)
        // clone the inflater using the ContextThemeWrapper
        val localInflater = inflater!!.cloneInContext(contextThemeWrapper)
        val view = localInflater!!.inflate(R.layout.fragment_2,container,false)
        recyclerView = view!!.findViewById<RecyclerView>(R.id.fragment2_recyclerview)
        initRecycleView()
        return view
    }

    fun initRecycleView(){
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val studentList:List<Student> =  LitePal.findAll(Student::class.java)
        val adpter = AlterGradeAdpter(studentList,activity)
        recyclerView.adapter = adpter
        //添加Android自带的分割线
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

}