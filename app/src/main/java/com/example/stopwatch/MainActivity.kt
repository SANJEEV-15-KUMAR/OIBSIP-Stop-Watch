package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isRunning = false

    private var timerSec =0

    private val handler = Handler(Looper.getMainLooper())

    private var count =0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textStart.setOnClickListener {

            if(count==0){
                Start()
                count++
            }
            else  if(count%2==0){
                Start()
                count++
            }
            else{
                stop()
                count++
            }



        }

        binding.TextRestart.setOnClickListener { Restart() }

    }



    private val runnable = object:Runnable{
        override fun run() {
            timerSec++

            val second = timerSec%60
            val minutes = (timerSec %3600)/60
            val hours = timerSec/3600

            val time = String.format("%02d:%02d:%02d",hours,minutes,second)
            binding.textTimer.text = time

            handler.postDelayed(this,1000)
        }

    }

    private fun Start(){
        if(!isRunning){
            handler.postDelayed(runnable,1000)
            isRunning = true


            binding.textStart.text ="Stop"

            binding.TextRestart.isEnabled = true
        }
    }


    private fun stop(){
        if(isRunning){
            handler.removeCallbacks(runnable)
            isRunning = false


            binding.textStart.text = "Resume"

            binding.TextRestart.isEnabled = true
        }
    }



    private fun Restart(){

        stop()
        count =0

        timerSec =0
        binding.textTimer.text = "00:00:00"
        
        binding.textStart.text = "Start"

        binding.TextRestart.isEnabled = false
    }
}