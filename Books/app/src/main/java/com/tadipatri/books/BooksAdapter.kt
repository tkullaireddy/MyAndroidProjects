package com.tadipatri.books


import android.content.Context
import android.content.Intent
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.tadipatri.books.BooksAdapter.BooksViewHolder

class BooksAdapter( val context: Context,  val books:List<Book>): Adapter<BooksViewHolder>(){

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
        val book: Book = books[position]
//        holder.imgBook.setImageResource(BooksViewHolder.get(position).poster)
        holder.tvAuthor.text = book.author
        holder.tvTitle.text = book.title
        holder.tvDesc.text = book.description

        Glide.with(context)
            .load(book.imageURL)
            .into(holder.imgBook)

        holder.v.setOnClickListener {
            Toast.makeText(context, book.downloadURL, Toast.LENGTH_LONG).show()


            val actInt: Intent = Intent(context, showBookDetail::class.java)
            actInt.putExtra ("BOOKDETAILS", book)
            actInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(actInt)



           // context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(book.downloadURL)))

        }
    }
}