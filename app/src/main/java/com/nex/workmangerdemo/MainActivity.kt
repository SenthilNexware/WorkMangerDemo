package com.nex.workmangerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.nex.workmangerdemo.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.button.setOnClickListener {
            //setOneTimeWorkRequest()
           periodicWorkRequest()
        }


    }

    private fun setOneTimeWorkRequest(){
      val workManager=  WorkManager.getInstance(applicationContext)
        val constraints=Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresCharging(true)
            .build()

        val data :Data=Data.Builder()
            .putInt("Key_count",10)
            .build()
        val filterWorker=OneTimeWorkRequest.Builder(FilterWorker::class.java)
            .build()
        val compressWorker=OneTimeWorkRequest.Builder(CompressWorker::class.java)
            .build()

        val downloadWorker=OneTimeWorkRequest.Builder(DownloadWorker::class.java)
            .build()

        val paralleWorker= mutableListOf<OneTimeWorkRequest>()
        paralleWorker.add(downloadWorker)
        paralleWorker.add(filterWorker)

        val uploadRequest=OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        workManager
            .beginWith(paralleWorker)
            .then(compressWorker)
            .then(uploadRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {

            binding.textView.text=it.state.name

            if(it.state.isFinished){
                binding.textView.text=""
                val data=it.outputData
                val message=data.getString("WORK_OUT_PUT")
                binding.textView.text=it.state.name +"\n"+message
            }
        })
    }


    private fun periodicWorkRequest(){
        val periodicWorkRequest =PeriodicWorkRequest.Builder(DownloadWorker::class.java,16,TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}