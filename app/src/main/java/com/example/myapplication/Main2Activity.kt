package com.example.myapplication

import android.app.Activity
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.flipboard.bottomsheet.BottomSheetLayout
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.Exception




class Main2Activity : AppCompatActivity() {

    val adapterObj: MainAdapter = MainAdapter(this, data.filter { it.type == TYPE_PERSONAL })

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        listView.adapter = adapterObj
        listView.layoutManager = LinearLayoutManager(this)

        navBar.setOnNavigationItemSelectedListener{ setData(it.itemId) }

        DataLoader(this).load()

        btnAdd.setOnClickListener { showAddForm() }
    }

    fun setData(itemId: Int = R.id.item1): Boolean {
        when (itemId) {
            R.id.item1 -> {
                adapterObj.items = data.filter { it.type == TYPE_PERSONAL }
                app_bar_image.setImageResource(R.drawable.linux)
                btnAddWrap.visibility = View.VISIBLE
            }
            R.id.item2 -> {
                adapterObj.items = data.filter { it.type == TYPE_GROUP || it.type == TYPE_ADV }
                app_bar_image.setImageResource(R.drawable.win)
                btnAddWrap.visibility = View.INVISIBLE
            }
            else -> {}
        }

        Log.d("current", adapterObj.dataSet.toString())
        adapterObj.notifyDataSetChanged()
        return true
    }


    fun addToData(v: View) {
        val wrap = v.parent as? LinearLayout ?: return

        wrap.apply{
            data.add(data.size, PersonalItem(
                findViewById<EditText>(R.id.newItemTitle).text.toString(),
                findViewById<EditText>(R.id.newItemText).text.toString()
            ))
        }
        val tmpService = getSystemService(Activity.INPUT_METHOD_SERVICE)
        if(tmpService is InputMethodManager)
            tmpService.hideSoftInputFromWindow(v.windowToken, 0)

        this.findViewById<BottomSheetLayout>(R.id.popupWrap).dismissSheet()
        setData()
    }

    private fun showAddForm() {
        val popupNode = LayoutInflater.from(this)
            .inflate(R.layout.popup_add, findViewById(R.id.imageView), false)

        this.findViewById<BottomSheetLayout>(R.id.popupWrap).showWithSheetView(popupNode)

    }

}
