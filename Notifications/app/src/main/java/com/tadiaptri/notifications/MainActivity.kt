package com.tadiaptri.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

     var counter=0
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel1: NotificationChannel

    lateinit var txt: TextInputLayout
     lateinit var notificationChannel2: NotificationChannel
    lateinit var n1: Notification
    lateinit var n2: Notification

    companion object {
        val CHANNEL_ID1 = "NOTI-1"
        val CHANNEL_ID2 = "NOTI-2"
        val NOTIFICATION_ID = 23
        val key = "KEY1"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counter = counter+1
        // var str = if (savedInstanceState != null) intent.extras?.getString("key1") else ""


        txt = this.findViewById<TextInputLayout>(R.id.txtNotiText)
        val str = intent?.getStringExtra(key)


        when (str) {
            "NOT1-1" -> {
                notificationManager.cancel(NOTIFICATION_ID)
                txt.editText?.setText("Notification 1 Action 1")
            }

            "NOT1-2" -> {
                notificationManager.cancel(NOTIFICATION_ID)
                txt.editText?.setText("Notification 1 Action 2")
            }

            "NOT1-3" -> {
                notificationManager.cancel(NOTIFICATION_ID)
                txt.editText?.setText("Notification 1 Action 3")
            }

            "NOT2-1" -> {
                notificationManager.cancel(NOTIFICATION_ID + 1)
                txt.editText?.setText("Notification 2 Action 1")
            }

            "NOT2-2" -> {
                notificationManager.cancel(NOTIFICATION_ID + 1)
                txt.editText?.setText("Notification 2 Action 2")
            }

            "NOT2-3" -> {
                notificationManager.cancel(NOTIFICATION_ID + 1)
                txt.editText?.setText("Notification 2 Action 3")
            }

            else -> {
                txt.editText?.setText("Kullai reddy T")
            }
        }


        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel1 = NotificationChannel(
                CHANNEL_ID1, "Kullai1",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel2 = NotificationChannel(
                CHANNEL_ID2, "Kullai2",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel1)
            notificationManager.createNotificationChannel(notificationChannel2)
        }

    }

    fun sendNotifications(view: View) {

        //TODO 4: You can bring the notifications alive
        // Pending intent is the description of the action to be performed on another component as if it is doing the
        // job on the current context.

//        val bundle = Bundle()
//        bundle.putString("key1","KULLAI REDDY T")

        val i11 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT1-1")
        val i12 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT1-2")
        val i13 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT1-3")
        val i21 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT2-1")
        val i22 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT2-2")
        val i23 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT2-3")
        val i1 = Intent(this, MainActivity::class.java).putExtra(key, "NOT1-1")
        val i2 = Intent(this, MainActivity::class.java).putExtra("key1", "NOT2")

//        i11.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        i12.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        i13.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        i21.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        i22.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        i23.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
//        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//


        val pi1 = PendingIntent.getActivity(this, 1, i1, PendingIntent.FLAG_IMMUTABLE)
        val pi11 = PendingIntent.getActivity(this, 2, i11, PendingIntent.FLAG_IMMUTABLE)
        val pi12 = PendingIntent.getActivity(this, 3, i12, PendingIntent.FLAG_IMMUTABLE)
        val pi13 = PendingIntent.getActivity(this, 4, i13, PendingIntent.FLAG_IMMUTABLE)
        val pi2 = PendingIntent.getActivity(this, 5, i2, PendingIntent.FLAG_IMMUTABLE)
        val pi21 = PendingIntent.getActivity(this, 6, i21, PendingIntent.FLAG_IMMUTABLE)
        val pi22 = PendingIntent.getActivity(this, 7, i22, PendingIntent.FLAG_IMMUTABLE)
        val pi23 = PendingIntent.getActivity(this, 8, i23, PendingIntent.FLAG_IMMUTABLE)


        // tODO 5: If you ever want to display an image in a notification, convert the image to bitmap format
        val b: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.notif)

        // TODO 3: Build your notification to push it to users
        n1 = NotificationCompat.Builder(this, CHANNEL_ID1)
            /*You write the entire build code here*/
            .setSmallIcon(R.drawable.baseline_emoji_food_beverage_24)
            .setContentTitle(txt.editText?.text.toString() + counter.toString())
            .setContentText("Notification 1 with message" +txt.editText?.text)
            .setContentIntent(pi1)
            .addAction(R.drawable.baseline_emoji_food_beverage_24, "Action 1", pi11)
            .addAction(R.drawable.baseline_emoji_food_beverage_24, "Action 2", pi12)
            .addAction(R.drawable.baseline_emoji_food_beverage_24, "Action 3", pi13)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(b))
            .setAutoCancel(true)
            .build()

        n2 = NotificationCompat.Builder(this, CHANNEL_ID2)
            /*You write the entire build code here*/
            .setSmallIcon(R.drawable.baseline_emoji_food_beverage_24)
            .setContentTitle(txt.editText?.text.toString() + counter.toString())
            .setContentText("Notification 2 with message" + txt.editText?.text)
            .setContentIntent(pi2)
            .addAction(R.drawable.baseline_emoji_food_beverage_24, "Action 1", pi21)
            .addAction(R.drawable.baseline_emoji_food_beverage_24, "Action 2", pi22)
            .addAction(R.drawable.baseline_emoji_food_beverage_24, "Action 3", pi23)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(b))
            .setAutoCancel(true)
            .build()


        notificationManager.notify(NOTIFICATION_ID, n1)
        notificationManager.notify(NOTIFICATION_ID + 1, n2)

        counter++
    }

    fun clearNotifications(view: View) {
        notificationManager.cancel(NOTIFICATION_ID)
        notificationManager.cancel(NOTIFICATION_ID + 1)
    }


}