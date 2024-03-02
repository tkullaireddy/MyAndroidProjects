package com.tadipatri.books

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tadipatri.books.BooksAdapter.BooksViewHolder

class BooksAdapter(val context: Context, val books:List<Book>): Adapter<BooksViewHolder>(){

    class BooksViewHolder(val v: View): ViewHolder(v){
        val imgBook: ImageView = v.findViewById(R.id.imgBook)
        val tvTitle: TextView = v.findViewById(R.id.tvBookTitle)
        val tvAuthor:TextView = v.findViewById(R.id.tvBookAuthor)
        val tvDesc:TextView = v.findViewById(R.id.tvBookDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val v:View = LayoutInflater.from(context).inflate(R.layout.viewoneboook,parent,false)
        return BooksViewHolder(v)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book : Book = books.get(position)
//        holder.imgBook.setImageResource(BooksViewHolder.get(position).poster)


        holder.v.setOnClickListener { view->
            Toast.makeText(context,book.author,Toast.LENGTH_LONG).show()
//            val favMovie = movies.get(position)
//            context.startActivity(Intent(context,DetailsActivity::class.java).putExtra("DATA",favMovie))
        }
    }
}