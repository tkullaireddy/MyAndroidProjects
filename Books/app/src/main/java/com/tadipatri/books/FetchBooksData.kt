package com.tadipatri.books

import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.io.Serializable
import java.net.URL
import java.net.URLConnection
import java.util.Scanner


data class Book(val title:String, val author:String,
                val description:String,val imageURL:String , val downloadURL:String ): Serializable


class FetchJoke(val context:Context, val tv:TextView, val pb:ProgressBar): AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String?): String {
        val url:URL = URL(params[0])
        //Establish a connection
        val connection:URLConnection = url.openConnection()
        // Read the data
        val ips:InputStream = connection.getInputStream()
        val builder: java.lang.StringBuilder = java.lang.StringBuilder()
        val s:Scanner = Scanner(ips)
        while (s.hasNext()){
            builder.append(s.nextLine())
        }
        return builder.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        // TODO: Parse the JSON Data and only display what is needed.
        val obj: JSONObject = JSONObject(result)
        val joke:String = obj.getString("value")
        pb.visibility = View.INVISIBLE
        tv.text = joke
        /*  val obj:JSONObject = JSONObject(result)
          val arr:JSONArray = obj.getJSONArray("items")
          tv.text = ""
          var i = 0
          while (i<arr.length()){
              val item = arr.getJSONObject(i)
              val volInfo = item.getJSONObject("volumeInfo")
              val title = volInfo.getString("title")
              tv.append("$title\n")
              i++
          }*/
    }
}

class FetchBooksData(val context: Context, val rvmain: RecyclerView, val pb: ProgressBar): AsyncTask<String, Void, String>(){
    override fun doInBackground(vararg params: String?): String {
        val url:URL = URL(params[0])
        //Establish a connection
        val connection: URLConnection = url.openConnection()
        // Read the data
        val ips: InputStream = connection.getInputStream()
        val builder:StringBuilder = StringBuilder()
        val s: Scanner = Scanner(ips)
        while (s.hasNext()){
            builder.append(s.nextLine())
        }

//        Toast.makeText(context,"Process data1", Toast.LENGTH_SHORT).show()
        return builder.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        // TODO: Parse the JSON Data and only display what is needed.
        val obj: JSONObject = JSONObject(result)
//        val joke: String = obj.getString("value")
        pb.visibility = View.INVISIBLE


//        rvmain.text = "OK"
//        Toast.makeText(context,"OK Process data1", Toast.LENGTH_SHORT).show()
        val a: BooksAdapter = BooksAdapter(context, processJsonData(obj))
        rvmain.adapter = a
        rvmain.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)


    }


    fun checkandJsonString(obj:JSONObject,key:String):String{
        if (obj.has(key)) {
            // key exists, access its value using:
            return obj.getString(key)
            // ... do something with the value
        }
        return "not found"
    }

    fun processJsonData( responce:JSONObject) :List<Book>
    {

        val retobj :MutableList<Book> = mutableListOf<Book>()


        val books: JSONArray = responce.getJSONArray("items")
        var i: Int =0
        while (i<books.length()) {
            val book: JSONObject = books.getJSONObject(i)

            val volInfo: JSONObject = book.getJSONObject("volumeInfo")
            val title: String = checkandJsonString(volInfo,"title")
            val description: String = checkandJsonString(volInfo,"description")
            val author: String = checkandJsonString(volInfo,"subtitle")
            val images: JSONObject = volInfo.getJSONObject("imageLinks")


            val imageURL: String = checkandJsonString(images,"thumbnail")
            val downloadURL: String = checkandJsonString(book,"selfLink")

            val singlebook: Book = Book(title, author, description, imageURL, downloadURL)
            retobj.add(singlebook)

            i++

        }



        return retobj

    }


}