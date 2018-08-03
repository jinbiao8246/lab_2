package com.example.ace.lab_2.dialog;

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

/**
 * Created by ace on 2018/6/25.
 */

public class AddStudent extends DialogFragment {

    public interface Callback{
        void onClick(String number,String name,String phone,String major);
    }

    private Callback callback;

    public void show(FragmentManager fragmentManager){
        show(fragmentManager,"AddStudent");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_student,null);
        builder.setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback!=null){
                            Log.e("dianji","hahah");
                            EditText number = view.findViewById(R.id.add_number);
                            EditText name = view.findViewById(R.id.add_name);
                            EditText phone = view.findViewById(R.id.add_phone);
                            EditText major = view.findViewById(R.id.add_major);
                            callback.onClick(number.getText().toString(),name.getText().toString(),phone.getText().toString(),major.getText().toString());
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback){
            callback = (Callback)context;
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
