package com.livinglifetechway.quickpermissions_sample.java;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.livinglifetechway.quickpermissions.annotations.WithPermissions;
import com.livinglifetechway.quickpermissions_sample.R;

public class AutoHandleJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_handle_java);

        autoHandlePermissions();
    }

    @WithPermissions(permissions = {Manifest.permission.CAMERA})
    private void autoHandlePermissions() {
        Toast toast = Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
