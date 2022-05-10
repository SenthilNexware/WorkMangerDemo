package com.nex.workmangerdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context,parameters: WorkerParameters) :Worker(context,parameters) {
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        try {
            val count =inputData.getInt("Key_count",0)
            for(i in 0..count){
                Log.i("MYTAG","WorkManger is Running $i")
            }

            val time =SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val currentTime=time.format(Date())
            val output=Data.Builder()
                .putString("WORK_OUT_PUT",currentTime)
                .build()

            return Result.success(output)
        }catch (e:Exception){
            return Result.failure()
        }

    }
}