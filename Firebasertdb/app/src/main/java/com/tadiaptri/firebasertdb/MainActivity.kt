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
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.provider.MediaStore
import android.widget.ImageView
import androidx.camera.core.ImageCaptureException
import androidx.core.net.toUri
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    lateinit var databseName: String
    lateinit var reference: DatabaseReference
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val v = binding.root
        setContentView(v)

        databseName = getString(R.string.dbname)


        reference = Firebase.database.reference

        binding.progressBar.visibility = ProgressBar.INVISIBLE

        binding.imageView.setOnClickListener { GetImageFromCameraX() }

        binding.saveBtn.setOnClickListener {
            // tODO write code to save data on to firebase real time databases
            binding.progressBar.visibility = ProgressBar.VISIBLE
            val name: String = binding.personName.text.toString()
            val age: Int = binding.personAge.text.toString().toInt()
            val imageString =
                ImageConversionUtilities.getImageStringFromImageview(binding.imageView.drawable)

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

    }

    private fun loaddataFromDB() {
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
                Toast.makeText(applicationContext, "Data Not ready", Toast.LENGTH_LONG).show()
            }
        }

        reference.child(databseName).addValueEventListener(postListener)
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == requestCode && resultCode == Activity.RESULT_OK) {
//            val result = data?.getStringExtra("resultKey") ?: ""
//            // Use the 'result' variable here
//
//            val resultIntent = Intent()
//            resultIntent.putExtra("resultKey", result)
//
//        }
//    }


    private fun GetImageFromCameraX() {


        val intent = Intent(this, CameraMainActivity::class.java)
        intent.putExtra("dataKey", "Some data to send")
        val requestCode = 1 // Choose a unique code for your request

        startActivityForResult(intent, requestCode)


        //intent.data


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCode && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("resultKey") ?: ""

            if (result!="")
                binding.imageView.setImageURI(result.toUri())
            // Use the 'result' variable here
        }
    }


}

