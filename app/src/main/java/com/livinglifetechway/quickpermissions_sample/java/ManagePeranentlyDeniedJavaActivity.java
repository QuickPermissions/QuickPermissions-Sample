package com.livinglifetechway.quickpermissions_sample.java;

import android.Manifest;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.livinglifetechway.quickpermissions.annotations.OnPermissionsPermanentlyDenied;
import com.livinglifetechway.quickpermissions.annotations.OnShowRationale;
import com.livinglifetechway.quickpermissions.annotations.WithPermissions;
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest;
import com.livinglifetechway.quickpermissions_sample.R;

public class ManagePeranentlyDeniedJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_peranently_denied_java);

        methodRequiresPermissions();
    }


    @WithPermissions(permissions = {Manifest.permission.ACCESS_FINE_LOCATION})
    private void methodRequiresPermissions() {
        Toast toast = Toast.makeText(this, "Location permission granted", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @OnPermissionsPermanentlyDenied
    public void permissionsPermanentlyDenied(final QuickPermissionsRequest req) {
        // this will be called when some/all permissions required by the method are permanently
        // denied. Handle it your way.
        new AlertDialog.Builder(this)
                .setTitle("Permissions Denied")
                .setMessage("This is the custom permissions permanently denied dialog. " +
                        "Please open app settings to open app settings for allowing permissions, " +
                        "or cancel to end the permission flow.")
                .setPositiveButton("App Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        req.openAppSettings();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        req.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }

}
