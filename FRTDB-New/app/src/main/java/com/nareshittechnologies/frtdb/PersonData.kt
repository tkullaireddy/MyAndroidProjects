package com.nareshittechnologies.frtdb

import android.graphics.Bitmap
import android.graphics.BitmapFactory


data class PersonData(val name:String, val age:Int, val img: String) {
    constructor() : this("data", 3, "") {

    }
}


data class PersonDatawithID(val id:String, val name:String, val age:Int,val img:String)

//val storageRef = FirebaseStorage.getInstance().getReference("images/imageName.jpg")
//val bitmapByteArray = ByteArrayOutputStream()
//bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bitmapByteArray) // Adjust quality as needed
//
//val uploadTask = storageRef.putBytes(bitmapByteArray.toByteArray())
//uploadTask.addOnSuccessListener { snapshot ->
//    snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { downloadUrl ->
//        // Store downloadUrl in Firebase Realtime Database
//        val databaseReference = FirebaseDatabase.getInstance().getReference("imageUrls")
//        databaseReference.setValue(downloadUrl.toString())
//    }
//}