package com.example.ace.lab_2

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.inflate
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.ace.lab_2.adpter.AddStudentAdpter
import com.example.ace.lab_2.adpter.AlterGradeAdpter
import com.example.ace.lab_2.dialog.AddStudent
import com.example.ace.lab_2.dialog.AlterGrade
import com.example.ace.lab_2.dialog.AlterStudent
import com.example.ace.lab_2.enity.Student
import com.example.ace.lab_2.fragment.fragment_1
import com.example.ace.lab_2.fragment.fragment_2
import com.example.ace.lab_2.fragment.fragment_3
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.act
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.litepal.LitePal

class MainActivity : AppCompatActivity(),BottomNavigationBar.OnTabSelectedListener,AddStudent.Callback,AlterStudent.Callback,AlterGrade.Callback{

    private var fragments: ArrayList<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        bottom_navigation_bar.activeColor = R.color.colorAccent //选中颜色
        bottom_navigation_bar
                .addItem(BottomNavigationItem(R.drawable.ic_school_black_24dp,"学生"))
                .addItem(BottomNavigationItem(R.drawable.ic_assignment_black_24dp,"管理"))
                .addItem(BottomNavigationItem(R.drawable.ic_search_black_24dp,"查询"))

        bottom_navigation_bar.setFirstSelectedPosition(0)
        bottom_navigation_bar.initialise()
        fragments = getFragmentts()
        setDefaultFragment()
        bottom_navigation_bar.setTabSelectedListener(this)
    }

    @SuppressLint("CommitTransaction", "ResourceType")
    private fun setDefaultFragment(){
        val fm: android.support.v4.app.FragmentManager? = supportFragmentManager
        val transaction = fm!!.beginTransaction()
        transaction.replace(R.id.mFrame, fragment_1())
        transaction.commit()
    }

    private fun getFragmentts():ArrayList<Fragment>{
        val fragments = ArrayList<Fragment>()
        fragments.add(fragment_1())
        fragments.add(fragment_2())
        fragments.add(fragment_3())
        return  fragments
    }

    override fun onTabReselected(position: Int) {
        //当被选中的item再一次被点击时调用此方法
    }

    override fun onTabUnselected(position: Int) {
        //对没有选中的item进行处理的方法
        if (fragments != null){
            if (position< fragments!!.size){
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                val fragment = fragments!![position]
                //隐藏当前的fragment
                ft.hide(fragment)
                ft.commitAllowingStateLoss()
            }
        }
    }

    @SuppressLint("ResourceType", "CommitTransaction")
    override fun onTabSelected(position: Int) {
        //点击item时调用此方法
        if (fragments !=null ){
            if (position< fragments!!.size){
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                //当前的fragment
                val from = fm.findFragmentById(R.id.mFrame)
                //点击即将跳转的fragment
                val fragment = fragments!![position]
                if (fragment.isAdded){
                    //隐藏当前fragment，显示下一个
                    ft.hide(from).replace(R.id.mFrame,fragment)
                }else{
                    //隐藏当前的fragment，add下一个到activity中
                    ft.hide(from).replace(R.id.mFrame,fragment)
                    if (fragment.isHidden){
                        ft.show(fragment)
                    }
                }
                ft.commitAllowingStateLoss()
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onClick(number: String?, name: String?, phone: String?, major: String?) {
        when{
            number=="" -> toast("学号不能为空！")
            name=="" -> toast("姓名不能为空！")
            phone=="" -> toast("电话号码不能为空！")
            major=="" -> toast("专业不能为空！")
            else -> {
                val student = Student(number,name,phone,major)
                val mstudent:List<Student> = LitePal.findAll(Student::class.java)
                var state = true
                for (i in 0 until mstudent.size){
                    if (student.number==mstudent[i].number){
                        state = false
                    }
                }
                if (state){
                    student.save()
                    toast("添加成功！")
                }else{
                    toast("失败了，已存在相同学号！")
                }

                val view =  supportFragmentManager.findFragmentById(R.id.mFrame).view!!.findViewById<RecyclerView>(R.id.fragment1_recyclerview)
                val layoutManager = LinearLayoutManager(act)
                view.layoutManager = layoutManager
                val studentList:List<Student> =  LitePal.findAll(Student::class.java)
                val adpter = AddStudentAdpter(studentList,act, AddStudentAdpterCallBack())
                view.adapter = adpter
            }
        }
    }

    inner class AddStudentAdpterCallBack:AddStudentAdpter.AddStudentAdpterCallBack{
        override fun AddStudentAdpterCallBackOnclik() {
            val view =  supportFragmentManager.findFragmentById(R.id.mFrame).view!!.findViewById<RecyclerView>(R.id.fragment1_recyclerview)
            val layoutManager = LinearLayoutManager(act)
            view.layoutManager = layoutManager
            val studentList:List<Student> =  LitePal.findAll(Student::class.java)
            val adpter = AddStudentAdpter(studentList,act, AddStudentAdpterCallBack())
            view.adapter = adpter
        }

    }

    override fun alterStudentOnClick(number: String?, name: String?, phone: String?, major: String?, student: Student) {
        when{
            number=="" -> toast("学号不能为空！")
            name=="" -> toast("姓名不能为空！")
            phone=="" -> toast("电话号码不能为空！")
            major=="" -> toast("专业不能为空！")
            else -> {
                val studentNow = Student(number,name,phone,major,"alter")
                var state = true
                if (studentNow.number!=student.number){
                    val mstudent:List<Student> = LitePal.findAll(Student::class.java)
                    for (i in 0 until mstudent.size){
                        if (studentNow.number==mstudent[i].number){
                            state = false
                        }
                    }
                }
                if (state){
                    studentNow.updateAll("number = ?",student.number)
                    toast("修改成功！")
                }else{
                    toast("失败了，学号不能重复！")
                }

                val view =  supportFragmentManager.findFragmentById(R.id.mFrame).view!!.findViewById<RecyclerView>(R.id.fragment1_recyclerview)
                val layoutManager = LinearLayoutManager(act)
                view.layoutManager = layoutManager
                val studentList:List<Student> =  LitePal.findAll(Student::class.java)
                val adpter = AddStudentAdpter(studentList,act, AddStudentAdpterCallBack())
                view.adapter = adpter
            }
        }
    }

    override fun alterGradeOnClick(number: String?, name: String?, theorygrage: String?, labgrade: String?, student: Student) {
        when{
            theorygrage=="" -> toast("理论成绩不能为空！")
            labgrade=="" -> toast("实验成绩不能为空！")
            else -> {
                student.labgrade = labgrade
                student.theorygrage = theorygrage
                student.updateAll("number = ?",student.number)
                toast("修改成功！")
                val view =  supportFragmentManager.findFragmentById(R.id.mFrame).view!!.findViewById<RecyclerView>(R.id.fragment2_recyclerview)
                val layoutManager = LinearLayoutManager(act)
                view.layoutManager = layoutManager
                val studentList:List<Student> =  LitePal.findAll(Student::class.java)
                val adpter = AlterGradeAdpter(studentList,act)
                view.adapter = adpter
            }
        }
    }


}
