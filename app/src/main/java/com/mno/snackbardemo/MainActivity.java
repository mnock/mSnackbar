package com.mno.snackbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mno.msnackbar.MSnackBar;
import com.mno.msnackbar.OnActionClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FrameLayout main_content = (FrameLayout) findViewById(R.id.activity_main);
        Button btnTop = (Button) findViewById(R.id.btn_top);
        final MSnackBar a = new MSnackBar.Builder(MainActivity.this)
                .setTitle(R.string.cookie_title)
                .setMessage(R.string.cookie_message)
                //.setDuration(5 * 1000)
                .create();
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!a.isShowing())
                    a.show();
            }
        });

        Button btnBottom = (Button) findViewById(R.id.btn_bottom);
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MSnackBar
                        .Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        //.setIcon(R.mipmap.ic_launcher)
                        //.setMessage(R.string.cookie_message)
                        .setLayoutGravity(Gravity.BOTTOM)
                        .setTitleSizeTextSize(14)
                        .setActionTextSize(12)
                        .setActionColor(android.R.color.holo_red_light)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        Button btnTopWithIcon = (Button) findViewById(R.id.btn_top_with_icon);
        btnTopWithIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MSnackBar.Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.cookie_message)
                        .setDuration(3000)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        Button btnCustom = (Button) findViewById(R.id.btn_custom);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MSnackBar.Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        .setMessage(R.string.cookie_message)
                        .setDuration(3000)
                        .setTitleSizeTextSize(25)
                        .setMessageTextSize(18)
                        .setActionTextSize(15)
                        .setBackgroundColor(R.color.colorPrimary)
                        .setActionColor(R.color.colorAccent)
                        .setTitleColor(android.R.color.white)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
                            }
                        })
                        //.show();
                        .show(main_content);
            }
        });
    }

}
