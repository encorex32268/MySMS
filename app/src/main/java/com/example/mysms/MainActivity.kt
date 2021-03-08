package com.example.mysms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat



class MainActivity : AppCompatActivity() {


    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 105
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPemission()
        val count = findViewById<EditText>(R.id.countEditText)
        val button  = findViewById<Button>(R.id.sendButton)

        button.setOnClickListener {
//            var sendCount = count.text.toString().toInt()
//            for (i in 1..sendCount) {
                sendMessage()
//            }


        }

    }
    private fun checkPemission(){

        val sms_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        val permissions = arrayOf(Manifest.permission.SEND_SMS)
        if (sms_permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity, permissions,
            MY_PERMISSIONS_REQUEST_SEND_SMS)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@MainActivity,
                        "SMS Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show()
            }else{
                Toast.makeText(this@MainActivity,
                        "SMS Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
    private fun sendMessage(){
        val  smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("080-7176-0822",null,"test 123 ",null,null)
        Toast.makeText(this@MainActivity, "SMS sent.",
                Toast.LENGTH_LONG).show();
    }


}