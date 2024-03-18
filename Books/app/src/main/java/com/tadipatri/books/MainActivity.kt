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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvmain: RecyclerView = findViewById(R.id.rvMain)
        val txttopic: EditText = findViewById(R.id.txttopic)
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        val pbStatus: ProgressBar = findViewById(R.id.pbStatus)
        val fvbRefesh: FloatingActionButton = findViewById(R.id.floatingActionButton)

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

                doNetworkRequest("https://www.googleapis.com/books/v1/volumes?q=${txttopic.text}",pbStatus)

//                val bookdata: FetchBooksData = FetchBooksData(applicationContext, rvmain, pbStatus)
//
//                bookdata.execute("https://www.googleapis.com/books/v1/volumes?q=${txttopic.text}")
////                Snackbar.make(rvmain, "OK", Snackbar.LENGTH_LONG).show()

            } else {
                tvStatus.visibility = View.VISIBLE
                tvStatus.text = getString(R.string.no_internet_connection)
                Snackbar.make(rvmain, tvStatus.text, Snackbar.LENGTH_LONG).show()
            }
        }

    }


    private fun doNetworkRequest(link: String, pb: ProgressBar) {
        val queue:RequestQueue = Volley.newRequestQueue(this)

        val stringRequest:StringRequest = StringRequest(Request.Method.GET, link, { response ->
            pb.visibility = ProgressBar.INVISIBLE
            val g: Gson = Gson()
            val sd:Bookssearchdata = g.fromJson(response,Bookssearchdata::class.java)
            val rv: RecyclerView = findViewById(R.id.rvMain)
            val Books =processJsonData(sd.items)
            val adapter: BooksAdapter = BooksAdapter(applicationContext,Books )
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this)

        }, { error ->
            // Implementation goes here

        })

        queue.add(stringRequest)
    }

    fun processJsonData(  books:List<Items>) :List<Book> {
        val retobj: MutableList<Book> = mutableListOf<Book>()

        for (bk in books) {
            var authors: String = ""
            var ss = bk.volumeInfo?.authors
            var i = 0
            var cnt:Int =0
            cnt = ss?.size!!
            while (i < cnt) {
                authors += (if (i == 0) "" else ",") + ss[i]
                i++
            }
            val singlebook: Book =
                Book(
                    bk.volumeInfo?.title.toString(),
                    authors,
                    bk.volumeInfo?.description.toString(),
                    bk.volumeInfo?.imageLinks?.thumbnail.toString(),
                    bk.selfLink.toString()
                )
            retobj.add(singlebook)
        }

        return retobj

    }

}