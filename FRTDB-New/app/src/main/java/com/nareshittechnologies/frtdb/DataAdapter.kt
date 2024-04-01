package com.nareshittechnologies.frtdb

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import java.io.ByteArrayOutputStream


class PersonDataAdapter(val context: Context, val persons:List<PersonDatawithID>, val dbref:DatabaseReference):
    RecyclerView.Adapter<PersonDataAdapter.PersondataViewHolder>() {


    class PersondataViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        val imgview :ImageView=v.findViewById(R.id.photoview)
        val personName: TextView = v.findViewById(R.id.personName)
        val personAge: TextView = v.findViewById(R.id.personAge)
        val personID: TextView = v.findViewById(R.id.personID)
        val btnedit: Button = v.findViewById(R.id.btnUpdate)
        val btndelete: Button = v.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersondataViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.viewsingleobject, parent, false)
        return PersondataViewHolder(v)
    }

    override fun getItemCount(): Int {
        return persons.size
    }


    override fun onBindViewHolder(holder: PersondataViewHolder, position: Int) {
        val person = persons.get(position)

        holder.personName.text = person.name
        holder.personID.text = person.id
        holder.personAge.text = person.age.toString()

        if (   person.img!="" ) {
            val imageString = person.img// Your Base64 encoded image string

// Decode the Base64 string into a byte array
            val decodedBytes = Base64.decode(imageString, Base64.DEFAULT)

// Convert the byte array to a Bitmap
            val decodedBitmap: Bitmap? =
                BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

// Check if the decoding was successful
            if (decodedBitmap != null) {
                // Use the bitmap here
                holder.imgview.setImageBitmap(decodedBitmap)
            } else {
                // Handle the case where the decoding failed (optional)
                // You can display a placeholder image or handle the error
            }

        }


        holder.v.setOnClickListener {
            Toast.makeText(context, "${person.name} - ${person.age}  ID=${person.id}", Toast.LENGTH_SHORT)
                .show()
        }

        holder.btndelete.setOnClickListener {
            dbref.child("Personnew").child(person.id).removeValue()
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "${person.name} - ${person.age}  ID=${person.id} data is Removed",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
        holder.btnedit.setOnClickListener {
            if (holder.personAge.isEnabled) {
                holder.btnedit.text = "Edit"
                val drawable = holder.imgview.drawable // Handle null case if no drawable

                var imageString=""
                val b = when (drawable) {
                    is BitmapDrawable -> drawable.bitmap
                    else -> null
                }

                if (b==null)
                {
//                    Toast.makeText(context,
//                        "Image not found",
//                        Toast.LENGTH_LONG).show()
                }else
                {

                    val byteArrayOutputStream = ByteArrayOutputStream()
                    b.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream) // Adjust quality
                    val imageBytes = byteArrayOutputStream.toByteArray()

                     imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
                }


                val p: PersonData = PersonData(
                    holder.personName.text.toString(),
                    holder.personAge.text.toString().toInt(),
                    imageString
                );

                dbref.child("Personnew").child(person.id).setValue(p)
                    .addOnSuccessListener {
                        Toast.makeText(context,
                            "${person.name}  - ${person.age} changed to ${p.name} - ${p.age}  ID=${person.id} data is Updated",
                            Toast.LENGTH_LONG).show()
                    }
            } else {
                holder.btnedit.text = "Update"
                holder.personName.isEnabled = true
                holder.personAge.isEnabled = true
                holder.personName.requestFocus()
            }
        }

    }

}
