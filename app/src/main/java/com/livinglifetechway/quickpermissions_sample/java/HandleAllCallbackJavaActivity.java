package com.livinglifetechway.quickpermissions_sample.java;

import android.Manifest;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.livinglifetechway.quickpermissions.annotations.OnPermissionsDenied;
import com.livinglifetechway.quickpermissions.annotations.OnPermissionsPermanentlyDenied;
import com.livinglifetechway.quickpermissions.annotations.OnShowRationale;
import com.livinglifetechway.quickpermissions.annotations.WithPermissions;
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest;
import com.livinglifetechway.quickpermissions_sample.R;

public class HandleAllCallbackJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_all_callback_java);

        methodRequiresPermissions();
    }

    @WithPermissions(permissions = {Manifest.permission.WRITE_CALENDAR, Manifest.permission.RECORD_AUDIO})
    private void methodRequiresPermissions() {
        Toast toast = Toast.makeText(this, "Cal and microphone permission granted", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @OnShowRationale
    public void rationaleCallback(final QuickPermissionsRequest req) {
        // this will be called when permission is denied once or more time. Handle it your way
        new AlertDialog.Builder(this)
                .setTitle("Permissions Denied")
                .setMessage("This is the custom rationale dialog. Please allow us to proceed " +
                        "asking for permissions again, or cancel to end the permission flow.")
                .setPositiveButton("Go Ahead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        req.proceed();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        req.cancel();
                    }
                })
                .setCancelable(false)
                .show();
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


    @OnPermissionsDenied
    public void whenPermAreDenied(final QuickPermissionsRequest req) {
        // handle something when permissions are not granted and the request method cannot be called
        Toast toast = Toast.makeText(this, req.getDeniedPermissions().length + " permission(s) denied. This feature will not work.", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
