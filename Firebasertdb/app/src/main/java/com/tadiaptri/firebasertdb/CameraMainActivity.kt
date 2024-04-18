package com.tadiaptri.firebasertdb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tadiaptri.firebasertdb.databinding.ActivityCameraMainBinding

class CameraMainActivity : AppCompatActivity() {
    lateinit var activityMain2Binding:ActivityCameraMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMain2Binding = ActivityCameraMainBinding.inflate(layoutInflater)
        setContentView(activityMain2Binding.root)
        val s:String? = intent.getStringExtra("IMAGE")
        activityMain2Binding.imageView.setImageURI(s?.toUri())
    }
}