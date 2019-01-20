package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    val adapterObj: MainAdapter = MainAdapter(this, data.filter{ it.type == TYPE_PERSONAL })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        listView.adapter = adapterObj
        listView.layoutManager = LinearLayoutManager(this)

        navBar.setOnNavigationItemSelectedListener{ setData(it) }
    }

    fun setData(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> { adapterObj.dataSet = data.filter { it.type == TYPE_PERSONAL } }
            R.id.item2 -> { adapterObj.dataSet = data.filter { it.type == TYPE_GROUP } }
            else -> {}
        }
        adapterObj.notifyDataSetChanged()
        return true
    }

}
