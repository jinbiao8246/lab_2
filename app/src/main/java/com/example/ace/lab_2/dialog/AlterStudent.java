package com.example.ace.lab_2.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.ace.lab_2.R;
import com.example.ace.lab_2.enity.Student;

/**
 * Created by ace on 2018/6/26.
 */

@SuppressLint("ValidFragment")
public class AlterStudent extends DialogFragment {
    private Student student;

    @SuppressLint("ValidFragment")
    public AlterStudent(Student student){
        this.student = student;
    }

    public interface Callback{
        void alterStudentOnClick(String number,String name,String phone,String major,Student student);
    }

    private Callback callback;

    public void show(FragmentManager fragmentManager){
        show(fragmentManager,"AlterStudent");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams")
        final View view = inflater.inflate(R.layout.dialog_alter_student,null);
        final EditText number = view.findViewById(R.id.alter_number);
        final EditText name = view.findViewById(R.id.alter_name);
        final EditText phone = view.findViewById(R.id.alter_phone);
        final EditText major = view.findViewById(R.id.alter_major);
        number.setText(student.getNumber());
        name.setText(student.getName());
        phone.setText(student.getPhone());
        major.setText(student.getMajor());
        builder.setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback!=null){
                            Log.e("dianji","hahah");
                            callback.alterStudentOnClick(number.getText().toString(),name.getText().toString(),phone.getText().toString(),major.getText().toString(),student);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddStudent.Callback){
            callback = (Callback) context;
        }else {
//            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callback = null;
    }

}
