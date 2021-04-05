package com.example.mysms

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mysms.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private val MY_PERMISSIONS_REQUEST_SEND = 105
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        checkPemission()

        binding.apply {
            phoneEditTextView.setText("08071760822")
            sendButton.setOnClickListener {
//                callPhone()
                getPhone()
            }
        }

    }

    private fun ActivityMainBinding.getPhone() {


    }

    private fun ActivityMainBinding.callPhone() {
        val packageManager = packageManager
        val phoneNumber = phoneEditTextView.text.toString()
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }


    private fun checkPemission() {

        val sms_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        val callphone_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        val permissions = arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE)
        val sms_permissionCheck = sms_permission != PackageManager.PERMISSION_GRANTED
        val call_permissionCheck = callphone_permission != PackageManager.PERMISSION_GRANTED

        if (sms_permissionCheck || call_permissionCheck) {
            ActivityCompat.requestPermissions(this@MainActivity, permissions,
                    MY_PERMISSIONS_REQUEST_SEND)
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_SEND) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity,
                        "SMS Call Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show()
            } else {
                Toast.makeText(this@MainActivity,
                        "SMS Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    private fun sendMessage() {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("080-7176-0822", null, "test 123 ", null, null)
        Toast.makeText(this@MainActivity, "SMS sent.",
                Toast.LENGTH_LONG).show();
    }


}

internal interface ITelephony {
    fun endCall(): Boolean
    fun answerRingingCall()
    fun silenceRinger()
}