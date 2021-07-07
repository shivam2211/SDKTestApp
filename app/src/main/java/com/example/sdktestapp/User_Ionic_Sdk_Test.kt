package com.example.sdktestapp

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.ionic.starter.IpvHelper
import kotlinx.android.synthetic.main.activity_user_test.*
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_user_ionic_sdk_test.*
import kotlinx.android.synthetic.main.activity_user_test.btn_start
import kotlinx.android.synthetic.main.activity_user_test.et_api_key
import kotlinx.android.synthetic.main.activity_user_test.et_client_id
import kotlinx.android.synthetic.main.activity_user_test.et_environment
import kotlinx.android.synthetic.main.activity_user_test.et_user_id

class User_Ionic_Sdk_Test : AppCompatActivity() {

    private var data = JSONObject()
    private val PERMISSION_REQUEST_CODE = 200
    private var module: String = "free"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_ionic_sdk_test)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkAndRequestPermissions();


        btn_start.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                checkAndRequestPermissions();


            /* Input params for the sdk */
//            data.put("client_id", et_client_id.text.toString())
//            data.put("api_key", et_api_key.text.toString())
//            data.put("module", module)
//            data.put("environment", et_environment.text.toString())
//            data.put("user_id", et_user_id.text.toString())
//            data.put("link_id", "")
//            data.put("portal", "user")
//            data.put("group", et_user_group.text.toString())

            data.put("client_id", "Shinhan")
            data.put("api_key", "Shinhan")
            data.put("module", module)
            data.put("environment", et_environment.text.toString())
            data.put("user_id", "7710978566dfashy")
            data.put("link_id", "")
            data.put("portal", "user")
            data.put("group", et_user_group.text.toString())

//            data.put("url", "https://faceailive.thinkanalytics.in:5581/")
            Log.d("DATA", "startIpv = $data")


            /* Initializing the kwik-sdk */
            IpvHelper.getInstance().startIpv(this, data.toString())
        }

        }
    private fun checkAndRequestPermissions(): Boolean {
        val camera = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )
        val wtite = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        )
        val read = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        )
        val listPermissionsNeeded: ArrayList<String> = ArrayList()
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toArray(arrayOfNulls<String>(listPermissionsNeeded.size)),
                PERMISSION_REQUEST_CODE
            );
            return false;
        }
        return true;
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        Log.d("in fragment on request", "Permission callback called-------")
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                val perms: MutableMap<String, Int> = HashMap()
                // Initialize the map with both permissions
                perms[Manifest.permission.CAMERA] =
                    PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.INTERNET] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.RECORD_AUDIO] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    var i = 0
                    while (i < permissions.size) {
                        perms[permissions[i]] = grantResults[i]
                        i++
                    }
                    // Check for both permissions
                    if (perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED &&
                        perms[Manifest.permission.INTERNET] == PackageManager.PERMISSION_GRANTED &&
                        perms[Manifest.permission.RECORD_AUDIO] == PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.d(
                            "in fragment on request",
                            "CAMERA & WRITE_EXTERNAL_STORAGE READ_EXTERNAL_STORAGE permission granted"
                        )
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d(
                            "in fragment on request",
                            "Some permissions are not granted ask again "
                        )
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.CAMERA
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.INTERNET
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.RECORD_AUDIO
                            )
                        ) {
                            showDialogOK("Camera and Storage Permission required for this app",
                                DialogInterface.OnClickListener { dialog, which ->
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                        }
                                    }
                                })
                        } else {
                            Toast.makeText(
                                this,
                                "Go to settings and enable permissions",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }
    }


    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }

    fun gotoResult(result: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("DATA", "parent requestCode = $requestCode")
        Log.d("DATA", "parent resultCode = $resultCode")

        if(requestCode == 55) {
            var data_string: String = data?.getStringExtra("data").toString()
            Log.d("DATA", "main requestCode = $data_string")
            gotoResult(data_string)
        }
    }
}
