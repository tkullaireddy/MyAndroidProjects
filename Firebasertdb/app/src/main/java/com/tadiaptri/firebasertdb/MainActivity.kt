package com.tadiaptri.firebasertdb

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.tadiaptri.firebasertdb.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream

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
}
