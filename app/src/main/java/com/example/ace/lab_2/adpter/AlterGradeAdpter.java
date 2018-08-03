package com.example.ace.lab_2.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ace.lab_2.R;
import com.example.ace.lab_2.dialog.AlterGrade;
import com.example.ace.lab_2.enity.Student;

import java.util.List;

/**
 * Created by ace on 2018/6/26.
 */

public class AlterGradeAdpter extends RecyclerView.Adapter<AlterGradeAdpter.ViewHolder>{

    private List<Student> studentList;
    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View alterGradeView;
        CardView cardView;
        TextView number;
        TextView name;
        TextView theorygrage;
        TextView labgrade;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            alterGradeView = itemView;
            number = itemView.findViewById(R.id.item_alter_grade_number);
            name = itemView.findViewById(R.id.item_alter_grade_name);
            theorygrage = itemView.findViewById(R.id.item_alter_grade_theorygrage);
            labgrade = itemView.findViewById(R.id.item_alter_grade_labgrade);
            cardView = itemView.findViewById(R.id.item_alter_grade_cardview);
            linearLayout = itemView.findViewById(R.id.item_alter_grade_linear);
        }
    }

    public AlterGradeAdpter(List<Student> studentList,Activity activity){
        this.studentList = studentList;
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alter_grade,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Student student = studentList.get(position);
                AlterGrade alterGrade = new AlterGrade(student);
                alterGrade.show(activity.getFragmentManager());
            }
        });

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.number.setText("学号："+student.getNumber());
        holder.name.setText("姓名："+student.getName());
        holder.labgrade.setText("实验成绩："+student.getLabgrade());
        holder.theorygrage.setText("理论成绩："+student.getTheorygrage());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

}
