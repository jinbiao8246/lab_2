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
import android.widget.TextView;

import com.example.ace.lab_2.R;
import com.example.ace.lab_2.enity.Student;

/**
 * Created by ace on 2018/6/26.
 */

@SuppressLint("ValidFragment")
public class AlterGrade extends DialogFragment {

    private Student student;

    @SuppressLint("ValidFragment")
    public AlterGrade(Student student){
        this.student = student;
    }

    public interface Callback{
        void alterGradeOnClick(String number,String name,String theorygrage,String labgrade,Student student);
    }

    private Callback callback;

    public void show(FragmentManager fragmentManager){
        show(fragmentManager,"AlterGrade");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams")
        final View view = inflater.inflate(R.layout.dialog_alter_grade,null);
        final TextView number = view.findViewById(R.id.dialog_alter_grade_number);
        final TextView name = view.findViewById(R.id.dialog_alter_grade_name);
        final EditText theorygrage = view.findViewById(R.id.dialog_alter_grade_theorygrage);
        final EditText labgrade = view.findViewById(R.id.dialog_alter_grade_labgrade);
        number.setText(student.getNumber());
        name.setText(student.getName());
        theorygrage.setText(student.getTheorygrage());
        labgrade.setText(student.getLabgrade());
        builder.setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback!=null){
                            Log.e("dianji","hahah");
                            callback.alterGradeOnClick(number.getText().toString(),name.getText().toString(),theorygrage.getText().toString(),labgrade.getText().toString(),student);
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
