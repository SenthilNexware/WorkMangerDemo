package com.nex.workmangerdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressWorker(context: Context, parameters: WorkerParameters) : Worker(context,parameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        try {

            for(i in 0..10){
                Log.i("MYTAG","CompressWorker is Running $i")
            }

            return Result.success()
        }catch (e:Exception){
            return Result.failure()
        }

    }
}