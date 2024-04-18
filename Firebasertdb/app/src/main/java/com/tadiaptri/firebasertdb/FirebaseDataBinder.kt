package com.tadiaptri.firebasertdb

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import java.io.ByteArrayOutputStream

data class PersonData(val name:String, val age:Int, val img: String) {
    constructor() : this("data", 3, "") {

    }
}
data class PersonDatawithID(val id:String, val name:String, val age:Int,val img:String)

class PersonDataAdapter(val context: Context, val persons:List<PersonDatawithID>,
                        val dbref: DatabaseReference): RecyclerView.Adapter<PersonDataAdapter.PersondataViewHolder>()
{

    class PersondataViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        val imgview : ImageView =v.findViewById(R.id.photoview)
        val personName: TextView = v.findViewById(R.id.personName)
        val personAge: TextView = v.findViewById(R.id.personAge)
        val personID: TextView = v.findViewById(R.id.personID)
        val btnedit: Button = v.findViewById(R.id.btnUpdate)
        val btndelete: Button = v.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersondataViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.activity_single_person, parent, false)
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

        if (person.img!="" ) {
            val decodedBitmap: Bitmap = ImageConversionUtilities.GetBitmapfromImageString(person.img)
            holder.imgview.setImageBitmap(decodedBitmap)
        }

        holder.v.setOnClickListener {
            Toast.makeText(context, "${person.name} - ${person.age}  ID=${person.id}", Toast.LENGTH_SHORT)
                .show()
        }

        holder.btndelete.setOnClickListener {
            dbref.child(context.getString(R.string.dbname )).child(person.id).removeValue()
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

                val imageString= ImageConversionUtilities.getImageStringFromImageview(holder.imgview.drawable)

                val p: PersonData = PersonData(
                    holder.personName.text.toString(),
                    holder.personAge.text.toString().toInt(),
                    imageString
                );

                dbref.child(context.getString(R.string.dbname)).child(person.id).setValue(p)
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
class ImageConversionUtilities {
    companion object {
        fun getImageStringFromImageview(drawable: Drawable): String {

            var imageString = ""
            val b = when (drawable) {
                is BitmapDrawable -> drawable.bitmap
                else -> null
            }

            if (b == null) {

            } else {

                val byteArrayOutputStream = ByteArrayOutputStream()
                b.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream) // Adjust quality
                val imageBytes = byteArrayOutputStream.toByteArray()

                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
            }
            return imageString
        }

        fun GetBitmapfromImageString(imgString: String): Bitmap {
            val decodedBytes = Base64.decode(imgString, Base64.DEFAULT)
            // Convert the byte array to a Bitmap
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }
    }
}