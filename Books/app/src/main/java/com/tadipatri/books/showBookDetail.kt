package com.tadipatri.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class showBookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_book_detail)

        var bk: Book = intent.extras?.getSerializable("BOOKDETAILS") as Book
        val img: ImageView = this.findViewById(R.id.imgBookdetail)
        val title: TextView = this.findViewById(R.id.tvBookTitledetail)
        val author: TextView = this.findViewById(R.id.tvBookAuthordetail)
        val desc: TextView = this.findViewById(R.id.tvBookDescriptionDetail)


        Glide.with(this)
            .load(bk.imageURL)
            .into(img)

        title.text = bk.title
        author.text = "Autors : ${bk.author}"
        desc.text = bk.description


    }
}