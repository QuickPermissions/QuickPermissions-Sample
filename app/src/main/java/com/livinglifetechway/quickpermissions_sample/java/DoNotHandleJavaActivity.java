package com.livinglifetechway.quickpermissions_sample.java;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.livinglifetechway.quickpermissions.annotations.OnPermissionsDenied;
import com.livinglifetechway.quickpermissions.annotations.WithPermissions;
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest;
import com.livinglifetechway.quickpermissions_sample.R;

public class DoNotHandleJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_not_handle_java);

        autoHandlePermissions();
    }

    @WithPermissions(
            permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE},
            rationaleMessage = "We require storage permissions to save images. Please allow us.",
            handlePermanentlyDenied = false  // dialogs are not shown when permissions are per. denied
    )
    private void autoHandlePermissions() {
        Toast toast = Toast.makeText(this, "Sensors permission granted", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
