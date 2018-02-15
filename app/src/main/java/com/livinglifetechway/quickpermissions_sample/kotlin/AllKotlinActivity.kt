package com.livinglifetechway.quickpermissions_sample.kotlin

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.livinglifetechway.quickpermissions.annotations.OnPermissionsDenied
import com.livinglifetechway.quickpermissions.annotations.OnPermissionsPermanentlyDenied
import com.livinglifetechway.quickpermissions.annotations.OnShowRationale
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest
import com.livinglifetechway.quickpermissions_sample.R

class AllKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_kotlin)

        methodRequiresPermissions()
    }

    @WithPermissions(permissions = [(Manifest.permission.WRITE_CALENDAR), (Manifest.permission.RECORD_AUDIO)])
    private fun methodRequiresPermissions() {
        val toast = Toast.makeText(this, "Cal and microphone permission granted", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    @OnShowRationale
    fun rationaleCallback(req: QuickPermissionsRequest) {
        // this will be called when permission is denied once or more time. Handle it your way
        AlertDialog.Builder(this)
                .setTitle("Permissions Denied")
                .setMessage("This is the custom rationale dialog. Please allow us to proceed " + "asking for permissions again, or cancel to end the permission flow.")
                .setPositiveButton("Go Ahead") { dialog, which -> req.proceed() }
                .setNegativeButton("cancel") { dialog, which -> req.cancel() }
                .setCancelable(false)
                .show()
    }

    @OnPermissionsPermanentlyDenied
    fun permissionsPermanentlyDenied(req: QuickPermissionsRequest) {
        // this will be called when some/all permissions required by the method are permanently
        // denied. Handle it your way.
        AlertDialog.Builder(this)
                .setTitle("Permissions Denied")
                .setMessage("This is the custom permissions permanently denied dialog. " +
                        "Please open app settings to open app settings for allowing permissions, " +
                        "or cancel to end the permission flow.")
                .setPositiveButton("App Settings") { dialog, which -> req.openAppSettings() }
                .setNegativeButton("Cancel") { dialog, which -> req.cancel() }
                .setCancelable(false)
                .show()
    }


    @OnPermissionsDenied
    fun whenPermAreDenied(req: QuickPermissionsRequest) {
        // handle something when permissions are not granted and the request method cannot be called
        val toast = Toast.makeText(this, req.deniedPermissions.size.toString() + " permission(s) denied. This feature will not work.", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

}
