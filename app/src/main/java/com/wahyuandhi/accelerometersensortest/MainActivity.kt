package com.wahyuandhi.accelerometersensortest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahyuandhi.accelerometersensortest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            binding.tvSumbuX.text = "Sumbu X : "+event.values[0].toString()
            binding.tvSumbuY.text = "Sumbu Y : "+event.values[1].toString()
            binding.tvSumbuZ.text = "Sumbu Z : "+event.values[2].toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}