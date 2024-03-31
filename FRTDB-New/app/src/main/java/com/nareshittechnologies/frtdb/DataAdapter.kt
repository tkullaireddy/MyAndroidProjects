package com.nareshittechnologies.frtdb

import android.content.Context
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference


class PersonDataAdapter(val context: Context, val persons:List<PersonDatawithID>, val dbref:DatabaseReference):
    RecyclerView.Adapter<PersonDataAdapter.PersondataViewHolder>() {


    class PersondataViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

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
        val veg = persons.get(position)

        holder.personName.text = veg.name
        holder.personID.text = veg.id
        holder.personAge.text = veg.age.toString()


        holder.v.setOnClickListener {
            Toast.makeText(context, "${veg.name} - ₹${veg.age}  ID=${veg.id}", Toast.LENGTH_SHORT)
                .show()
        }

        holder.btndelete.setOnClickListener {
            dbref.child("Person").child(veg.id).removeValue()
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "${veg.name} - ${veg.age}  ID=${veg.id} data is Removed",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
        holder.btnedit.setOnClickListener {
            if (holder.personAge.isEnabled) {
                holder.btnedit.text = "Edit"
                val p: PersonData = PersonData(
                    holder.personName.text.toString(),
                    holder.personAge.text.toString().toInt()
                );
                dbref.child("Person").child(veg.id).setValue(p)
                    .addOnSuccessListener {
                        Toast.makeText(context,
                            "${veg.name}  - ${veg.age} changeed to ${p.name} - ${p.age}  ID=${veg.id} data is Updated",
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
