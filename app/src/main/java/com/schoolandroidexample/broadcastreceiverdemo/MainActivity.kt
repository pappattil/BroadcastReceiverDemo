package com.schoolandroidexample.broadcastreceiverdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.schoolandroidexample.broadcastreceiverdemo.databinding.ActivityMainBinding
import com.schoolandroidexample.broadcastreceiverdemo.service.BroadcastService

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startService(Intent(this, BroadcastService::class.java))
        }
        binding.btnStop.setOnClickListener {
            stopService(Intent(this, BroadcastService::class.java))
        }

        requestNeededPermission()
    }

    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_BOOT_COMPLETED) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this,
                Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.PROCESS_OUTGOING_CALLS,
                    Manifest.permission.RECEIVE_BOOT_COMPLETED,
                    Manifest.permission.FOREGROUND_SERVICE
                ),
                101)
        } else {
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivity, "All perm granted", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this@MainActivity,
                        "Permissions NOT granted", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }




}