package com.tadipatri.books

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvmain: RecyclerView = findViewById(R.id.rvMain)
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        val pbStatus: ProgressBar = findViewById(R.id.pbStatus)
        val fvbRefesh: FloatingActionButton = findViewById(R.id.floatingActionButton)


        rvmain.visibility= View.INVISIBLE
        tvStatus.visibility= View.INVISIBLE
        fvbRefesh.setOnClickListener{
            val manager:ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val act_Conn:NetworkInfo?= manager.activeNetworkInfo
            tvStatus.text=""
            tvStatus.visibility= View.INVISIBLE
            if (act_Conn!= null && act_Conn.isConnectedOrConnecting)
            {
                rvmain.visibility = View.VISIBLE

                val bookdata:FetchBooksData =  FetchBooksData(this,rvmain,pbStatus)



            }
            else {
                tvStatus.visibility = View.VISIBLE
                tvStatus.text = getString(R.string.no_internet_connection)
                Snackbar.make(rvmain, tvStatus.text, Snackbar.LENGTH_LONG).show()


            }
        }

    }
}