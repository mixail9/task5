package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.Exception

class Main2Activity : AppCompatActivity() {

    public val adapterObj: MainAdapter = MainAdapter(this, data.filter { it.type == TYPE_PERSONAL })

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        listView.adapter = adapterObj
        listView.layoutManager = LinearLayoutManager(this)

        navBar.setOnNavigationItemSelectedListener{ setData(it) }
        try {
            DataLoader(this).load()
        } catch(e: Exception) {
            Log.e("current", e.toString())
        }
    }

    fun setData(item: MenuItem?): Boolean {
        var bannersCount = 0
        when (item?.itemId) {
            R.id.item1 -> { adapterObj.items = data.filter { it.type == TYPE_PERSONAL }; app_bar_image.setImageResource(R.drawable.linux) }
            R.id.item2 -> { adapterObj.items = data.filter { it.type == TYPE_GROUP || it.type == TYPE_ADV }; app_bar_image.setImageResource(R.drawable.win) }
            else -> {}
        }
        if(item == null) {
            adapterObj.items = data.filter { it.type == TYPE_PERSONAL }
            app_bar_image.setImageResource(R.drawable.linux)
        }

        Log.d("current", adapterObj.dataSet.toString())
        adapterObj.notifyDataSetChanged()
        return true
    }

}
