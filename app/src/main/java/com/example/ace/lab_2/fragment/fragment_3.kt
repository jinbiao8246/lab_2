package com.example.ace.lab_2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.ace.lab_2.R
import com.example.ace.lab_2.enity.Student
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.litepal.LitePal

/**
 * Created by ace on 2018/5/9.
 */
class fragment_3: Fragment() {
    @SuppressLint("InflateParams", "SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_3,null)
        val search = view.findViewById<Button>(R.id.fragment3_search)
        val ettext = view.findViewById<EditText>(R.id.fragment3_ettext)
        val number = view.findViewById<TextView>(R.id.fragment3_number)
        val name = view.findViewById<TextView>(R.id.fragment3_name)
        val major = view.findViewById<TextView>(R.id.fragment3_major)
        val phone = view.findViewById<TextView>(R.id.fragment3_phone)
        val thorygrade = view.findViewById<TextView>(R.id.fragment3_thorygrade)
        val labgrade = view.findViewById<TextView>(R.id.fragment3_labgrade)

        search.onClick {
            val str = ettext.text.toString()
            val studentList = LitePal.findAll(Student::class.java)
            var student:Student? = Student()
            student = null
            for (i in 0 until studentList.size){
                if (str==studentList[i].number){
                    student = studentList[i]
                }
            }
            if (student==null){
                number.text = "未搜索到！"
                name.text = ""
                major.text = ""
                phone.text = ""
                thorygrade.text = ""
                labgrade.text = ""
            }else{
                number.text = "学号： ${student.number}"
                name.text = "姓名： ${student.name}"
                major.text = "专业： ${student.major}"
                phone.text = "电话号码： ${student.phone}"
                thorygrade.text = "理论成绩： ${student.theorygrage}"
                labgrade.text = "实验成绩： ${student.labgrade}"
            }
        }

        return view
    }
}