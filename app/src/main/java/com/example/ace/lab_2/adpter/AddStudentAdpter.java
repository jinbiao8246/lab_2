package com.example.ace.lab_2.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ace.lab_2.R;
import com.example.ace.lab_2.dialog.AlterStudent;
import com.example.ace.lab_2.enity.Student;

import java.util.List;

/**
 * Created by ace on 2018/6/26.
 */

public class AddStudentAdpter extends RecyclerView.Adapter<AddStudentAdpter.ViewHolder>{

    private List<Student> studentList;
    private Activity activity;
    private AddStudentAdpterCallBack addStudentAdpterCallBack;


    public interface AddStudentAdpterCallBack{
        void AddStudentAdpterCallBackOnclik();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        TextView name;
        TextView major;
        TextView phone;
        Button alter;
        Button delete;

        public ViewHolder(View view){
            super(view);
            number = view.findViewById(R.id.item_add_student_number);
            name = view.findViewById(R.id.item_add_student_name);
            major = view.findViewById(R.id.item_add_student_major);
            phone = view.findViewById(R.id.item_add_student_phone);
            alter = view.findViewById(R.id.item_add_student_alter);
            delete = view.findViewById(R.id.item_add_student_delete);

        }
    }

    public AddStudentAdpter(List<Student> studentList,Activity activity, AddStudentAdpterCallBack addStudentAdpterCallBack){
        this.studentList = studentList;
        this.activity = activity;
        this.addStudentAdpterCallBack = addStudentAdpterCallBack;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_student,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Student student = studentList.get(position);
        holder.number.setText("学号："+student.getNumber());
        holder.name.setText("姓名："+student.getName());
        holder.major.setText("专业："+student.getMajor());
        holder.phone.setText("电话："+student.getPhone());
        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(activity)
                        .setTitle("提示")
                        .setMessage("你确定要删除吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                student.delete();
                                Toast.makeText(activity,"删除成功",Toast.LENGTH_SHORT).show();
                                addStudentAdpterCallBack.AddStudentAdpterCallBackOnclik();
                            }
                        })
                        .create().show();
            }
        });
        holder.alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                val addStudent  = AddStudent()
//                addStudent.show(activity.fragmentManager)
                AlterStudent alterStudent = new AlterStudent(student);
                alterStudent.show(activity.getFragmentManager());
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

}
