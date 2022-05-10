package com.nex.workmangerdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class FilterWorker(context: Context, parameters: WorkerParameters) : Worker(context,parameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        try {

            for(i in 0..10){
                Log.i("MYTAG","FilterWorker is Running $i")
            }

            return Result.success()
        }catch (e:Exception){
            return Result.failure()
        }

    }
}