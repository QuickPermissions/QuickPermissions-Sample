package com.livinglifetechway.quickpermissions_sample.java;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.livinglifetechway.quickpermissions.annotations.WithPermissions;
import com.livinglifetechway.quickpermissions_sample.R;

public class CustomMessageJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_message_java);

        autoHandlePermissions();
    }

    @WithPermissions(
            permissions = {Manifest.permission.BODY_SENSORS},
            rationaleMessage = "To track your fitness, we require body sensor permissions. " +
                    "Please try again.",
            permanentlyDeniedMessage = "Without sensor permissions we can not track your fitness. " +
                    "Please allow senors permissions from the app settings."
    )
    private void autoHandlePermissions() {
        Toast toast = Toast.makeText(this, "Sensors permission granted", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
