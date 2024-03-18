package com.tadipatri.books

import com.google.gson.annotations.SerializedName


data class Bookssearchdata (

  @SerializedName("kind"       ) var kind       : String?          = null,
  @SerializedName("totalItems" ) var totalItems : Int?             = null,
  @SerializedName("items"      ) var items      : ArrayList<Items> = arrayListOf()

)