package com.tadiaptri.contactsreadtodb

import android.content.ContentResolver
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    companion object {
        val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.button)
val languageLV = findViewById<ListView>(R.id.list)

        val contacts =getPhoneContacts()

        var lngList: ArrayList<String>
        lngList = ArrayList()

        // on below line we are adding items to our list

        for (ctn in contacts) {
            lngList.add(ctn.name + " " + ctn.number)
        }

        // on below line we are initializing adapter for our list view.
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            lngList as List<String?>
        )

        // on below line we are setting adapter for our list view.
        languageLV.adapter = adapter




//        btn.setOnClickListener { loadContacts() }
    }

    fun getPhoneContacts(): ArrayList<Contact> {
        val contactsList = ArrayList<Contact>()
        val contactsCursor = contentResolver?.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        if (contactsCursor != null && contactsCursor.count > 0) {
            val idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val numberIndex =
                contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            while (contactsCursor.moveToNext()) {
                val id = contactsCursor.getString(idIndex)
                val name = contactsCursor.getString(nameIndex)
                val number = contactsCursor.getString(numberIndex)
                if (name != null) {
                    contactsList.add(Contact(id, name, number))
                }
            }
            contactsCursor.close()
        }
        return contactsList
    }

//    private suspend fun getContactNumbers(): HashMap<String, ArrayList<String>> {
//        val contactsNumberMap = HashMap<String, ArrayList<String>>()
//        val phoneCursor: Cursor? = mApplication.contentResolver.query(
//            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//            null,
//            null,
//            null,
//            null
//        )
//        if (phoneCursor != null && phoneCursor.count > 0) {
//            val contactIdIndex = phoneCursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
//            val numberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
//            while (phoneCursor.moveToNext()) {
//                val contactId = phoneCursor.getString(contactIdIndex)
//                val number: String = phoneCursor.getString(numberIndex)
//                //check if the map contains key or not, if not then create a new array list with number
//                if (contactsNumberMap.containsKey(contactId)) {
//                    contactsNumberMap[contactId]?.add(number)
//                } else {
//                    contactsNumberMap[contactId] = arrayListOf(number)
//                }
//            }
//            //contact contains all the number of a particular contact
//            phoneCursor.close()
//        }
//        return contactsNumberMap
//    }
//
//    private suspend fun getContactEmails(): HashMap<String, ArrayList<String>> {
//        val contactsEmailMap = HashMap<String, ArrayList<String>>()
//        val emailCursor = mApplication.contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
//            null,
//            null,
//            null,
//            null)
//        if (emailCursor != null && emailCursor.count > 0) {
//            val contactIdIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)
//            val emailIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
//            while (emailCursor.moveToNext()) {
//                val contactId = emailCursor.getString(contactIdIndex)
//                val email = emailCursor.getString(emailIndex)
//                //check if the map contains key or not, if not then create a new array list with email
//                if (contactsEmailMap.containsKey(contactId)) {
//                    contactsEmailMap[contactId]?.add(email)
//                } else {
//                    contactsEmailMap[contactId] = arrayListOf(email)
//                }
//            }
//            //contact contains all the emails of a particular contact
//            emailCursor.close()
//        }
//        return contactsEmailMap
//    }


//    fun fetchContacts() {
//        viewModelScope.launch {
//            val contactsListAsync = async { getPhoneContacts() }
//            val contactNumbersAsync = async { getContactNumbers() }
//            val contactEmailAsync = async { getContactEmails() }
//
//            val contacts = contactsListAsync.await()
//            val contactNumbers = contactNumbersAsync.await()
//            val contactEmails = contactEmailAsync.await()
//
//            contacts.forEach {
//                contactNumbers[it.id]?.let { numbers ->
//                    it.numbers = numbers
//                }
//                contactEmails[it.id]?.let { emails ->
//                    it.emails = emails
//                }
//            }
//            _contactsLiveData.postValue(contacts)
//        }
//    }

}

data class Contact(val id: String, val name: String, val number:String)
