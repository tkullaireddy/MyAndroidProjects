package com.tadipatri.myvegetables

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


data class Vegetable(val name:String, val price:Double, val priceunit: String, val image:Int,val description:String)

class Vegetableadapter(val context: Context, val Vegetables:List<Vegetable>):
    RecyclerView.Adapter<Vegetableadapter.VegetableViewHolder>() {

    class VegetableViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val imgview: ImageView = v.findViewById(R.id.imageofVegetable)
        val vegname: TextView = v.findViewById(R.id.nameofVegetable)
        val vegprice: TextView = v.findViewById(R.id.priceofVegetable)
        val descofveg: TextView = v.findViewById(R.id.DescofVegetable)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetableViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.viewonevegvertical,parent,false)
        return VegetableViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Vegetables.size
    }


    override fun onBindViewHolder(holder: VegetableViewHolder, position: Int) {
        val veg = Vegetables.get(position)
        holder.imgview.setImageResource(veg.image)
        holder.vegname.text=veg.name
        holder.descofveg.text=veg.description
        holder.vegprice.text = "₹${veg.price} per ${veg.priceunit}"


        holder.v.setOnClickListener{
            Toast.makeText(context,"${veg.name} - ₹${veg.price} per ${veg.priceunit}", Toast.LENGTH_SHORT).show()
//            context.startActivity(Intent(context,DetailsActivity::class.java).putExtra("DATA",favMovie))

        }


    }

}
