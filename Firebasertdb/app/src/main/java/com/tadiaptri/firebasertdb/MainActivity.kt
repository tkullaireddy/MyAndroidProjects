package com.tadiaptri.firebasertdb

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.tadiaptri.firebasertdb.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.Manifest
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    lateinit var databseName:String
    lateinit var reference: DatabaseReference
    lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val v = binding.root
        setContentView(v)

        databseName = getString(R.string.dbname)


        // Request camera permissions
        if (allPermissionsGranted()) {
            //startCamera()
        } else {
            requestPermissions()
        }


        reference = Firebase.database.reference

        binding.progressBar.visibility = ProgressBar.INVISIBLE

        binding.saveBtn.setOnClickListener {
            // tODO write code to save data on to firebase real time databases
            binding.progressBar.visibility = ProgressBar.VISIBLE
            val name: String = binding.personName.text.toString()
            val age: Int = binding.personAge.text.toString().toInt()
            val imageString= ImageConversionUtilities.getImageStringFromImageview(binding.imageView.drawable)

            val p: PersonData = PersonData(name, age, imageString)
            // Now push this to frtdb
            reference.child(databseName).push().setValue(p).addOnSuccessListener { v ->
                Toast.makeText(applicationContext, "Data Published!", Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = ProgressBar.INVISIBLE
                binding.personName.text.clear()
                binding.personAge.text.clear()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Data not Published!", Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = ProgressBar.INVISIBLE
            }
        }

        binding.loadBtn.setOnClickListener {
            // tODO: write code to load Data from FRTDB
            loaddataFromDB()
        }

        loaddataFromDB()

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun loaddataFromDB(){
        binding.progressBar.visibility = ProgressBar.VISIBLE
        val postListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val persons = mutableListOf<PersonDatawithID>()

                for (s in snapshot.children) {
                    val id: String? = s.key
                    val p: PersonData = s.getValue(PersonData::class.java) as PersonData
//                        binding.result.append("${id}  ${p.name}  ${p.age}\n")
                    persons.add(PersonDatawithID(id!!, p.name, p.age, p.img))
                }

                val da = PersonDataAdapter(applicationContext, persons, reference)
                binding.rcvMain.adapter = da
                binding.rcvMain.layoutManager =
                    GridLayoutManager(applicationContext, 2)  //line(this,2)

                // Actual logic of reading the data starts here
                binding.progressBar.visibility = ProgressBar.INVISIBLE
                // Toast.makeText( applicationContext,"Data Fetched - ${persons.count()}", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBar.visibility = ProgressBar.INVISIBLE
                Toast.makeText(applicationContext,"Data Not ready", Toast.LENGTH_LONG).show()
            }
        }

        reference.child(databseName).addValueEventListener(postListener)
    }

    fun CapturePhoto(view: View) {
       // binding.imageView.visibility = ImageView.INVISIBLE
        startCamera()
    }

    private var imageCapture: ImageCapture? = null


    // Select back camera as a default
    var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    private fun startCamera() {

       // binding.viewFinder.visibility = View.VISIBLE
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))

        //binding.viewFinder.visibility = View.INVISIBLE
    }

    private lateinit var cameraExecutor: ExecutorService

    // For requesting the permissions
    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Handle Permission granted/rejected
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && it.value == false)
                    permissionGranted = false
            }
            if (!permissionGranted) {
                Toast.makeText(baseContext,
                    "Permission request denied",
                    Toast.LENGTH_SHORT).show()
            } else {
                startCamera()
            }
        }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    fun ChangeCameraDirection(view: View) {

         if(cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
             cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
        else
             cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        startCamera()
    }
}
