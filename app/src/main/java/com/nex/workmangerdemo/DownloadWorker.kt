package com.nex.workmangerdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class DownloadWorker(context: Context, parameters: WorkerParameters) : Worker(context,parameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        try {

            for(i in 0..10){
                Log.i("MYTAG","DownloadWorker is Running $i")
            }
            val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val currentTime=time.format(Date())
            Log.i("MYTAG","DownloadWorker Timing $currentTime")
            return Result.success()
        }catch (e:Exception){
            return Result.failure()
        }

    }
}
