package com.tadipatri.books

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.provider.FontsContractCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvmain: RecyclerView = findViewById(R.id.rvMain)
        val txttopic: EditText = findViewById(R.id.txttopic)
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        val pbStatus: ProgressBar = findViewById(R.id.pbStatus)
        val fvbRefesh: FloatingActionButton = findViewById(R.id.floatingActionButton)

//        Glide.with(this)
//            .load("http://books.google.com/books/content?id=YRFrAAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api")
//            .into(findViewById(R.id.imageView))

        rvmain.visibility = View.INVISIBLE
        tvStatus.visibility = View.VISIBLE
        tvStatus.text = "Refresh to load data"
        pbStatus.visibility = View.INVISIBLE
        fvbRefesh.setOnClickListener {
            val manager: ConnectivityManager =
                getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val act_Conn: NetworkInfo? = manager.activeNetworkInfo
            tvStatus.text = ""
            tvStatus.visibility = View.INVISIBLE
            if (act_Conn != null && act_Conn.isConnectedOrConnecting) {
                rvmain.visibility = View.VISIBLE
                tvStatus.visibility = View.VISIBLE




                pbStatus.visibility = View.VISIBLE
                val bookdata: FetchBooksData = FetchBooksData(this, rvmain, pbStatus)

                bookdata.execute("https://www.googleapis.com/books/v1/volumes?q=${txttopic.text}")
//                Snackbar.make(rvmain, "OK", Snackbar.LENGTH_LONG).show()

            } else {
                tvStatus.visibility = View.VISIBLE
                tvStatus.text = getString(R.string.no_internet_connection)
                Snackbar.make(rvmain, tvStatus.text, Snackbar.LENGTH_LONG).show()
            }
        }

    }
}