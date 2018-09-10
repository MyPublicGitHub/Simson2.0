package com.simson.www.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.simson.www.R;

public class WaitProgressDialog extends ProgressDialog {
    private Context context;

    public WaitProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDialog();
    }

    public void createDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.loading_dialog_layout, null);
        this.setCanceledOnTouchOutside(false);
        setContentView(view);
    }

//    public void setMessage(String message){
//        super.setMessage(message);
//    }

}
