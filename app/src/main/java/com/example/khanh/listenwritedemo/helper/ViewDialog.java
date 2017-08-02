package com.example.khanh.listenwritedemo.helper;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khanh.listenwritedemo.R;

/**
 * Created by khanh on 5/29/2017.
 */

public class ViewDialog {
    public void showDialog(Activity activity, String msg, int a){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);

        // Định dạng chiều cao và chiều rộng cho dialog
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // căn giữa các đối tượng
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        ImageView imgIcon = (ImageView)dialog.findViewById(R.id.imgIcon);

        //truong hop co mang
        if (a == 1){

        }else {
            imgIcon.setBackgroundColor(Color.rgb(218,95,106));
            imgIcon.setImageResource(R.drawable.b);
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
