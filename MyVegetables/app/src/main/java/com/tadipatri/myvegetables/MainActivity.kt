package com.tadipatri.myvegetables


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rvMain)
        val da = Vegetableadapter(this, createVegetableData() )
        rv.adapter=da
        rv.layoutManager = GridLayoutManager(this,1)  //line(this,2)

    }

    private fun createVegetableData(): List<Vegetable> {

        return mutableListOf(
            Vegetable(getString(R.string.onion), 20.0, getString(R.string.kg), R.drawable.onion, getString(R.string.onion_desc)),
            Vegetable(getString(R.string.tomato), 10.0, getString(R.string.kg), R.drawable.tomato, getString(R.string.tomato_desc)),
            Vegetable(getString(R.string.baingan), 15.0, getString(R.string.kg), R.drawable.baingan, getString(R.string.baingan_desc)),
            Vegetable(getString(R.string.bitter_guard), 25.0, getString(R.string.kg), R.drawable.bittergaurd, getString(R.string.bitter_guard_desc)),
            Vegetable(getString(R.string.bottle_guard), 20.0, getString(R.string.medium_piece), R.drawable.bottlegaurd, getString(R.string.bottle_guard_desc)),
            Vegetable(getString(R.string.broccoli), 80.0, getString(R.string.kg), R.drawable.bracoli, getString(R.string.broccoli_desc)),
            Vegetable(getString(R.string.capsicum_green), 70.0, getString(R.string.kg), R.drawable.capsicumgreen, getString(R.string.capsicum_green_desc)),
            Vegetable(getString(R.string.capsicum_red), 60.0, getString(R.string.kg), R.drawable.capsicumred, getString(R.string.capsicum_red_desc)),
            Vegetable(getString(R.string.carrot), 55.0, getString(R.string.kg), R.drawable.carrot, getString(R.string.carrot_desc)),
            Vegetable(getString(R.string.cucumber), 20.0, getString(R.string.kg), R.drawable.cucumber, getString(R.string.cucumber_desc)),
            Vegetable(getString(R.string.potato), 30.0, getString(R.string.kg), R.drawable.potato, getString(R.string.potato_desc)),
            Vegetable(getString(R.string.pumpkin), 45.0, getString(R.string.kg), R.drawable.pumpkin, getString(R.string.pumpkin_desc))
        )
    }

}


