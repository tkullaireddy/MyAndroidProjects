package com.tadiaptri.csvtosqlite

import java.io.File


fun main()
{
    //val filepath:String = "contacts.csv" // " sampledate/contacts.csv"
    val csv = File("app/sampledata/contacts.csv")

    csv.forEachLine { line->
        val items = line.split(',')
        println(items)
    }
}