package com.example.mysms

import android.R.id.message
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest


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
            for (i in 1..count.text.toString().toInt()) {
                sendMessage(i)
            }


        }

    }
    fun checkPemission(){

        val sms_permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)
        val permissions = arrayOf("android.Manifest.permission.SEND_SMS")
        if (sms_permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity, permissions,
            MY_PERMISSIONS_REQUEST_SEND_SMS)
        }else{
            sendMessage(0)
        }


    }

    fun sendMessage(number : Int ){
        val  smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("080-7176-0822",null,"test 123 $number",null,null)
        Toast.makeText(this@MainActivity, "SMS sent.",
                Toast.LENGTH_LONG).show();
    }


}