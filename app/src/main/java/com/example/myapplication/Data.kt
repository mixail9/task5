package com.example.myapplication

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

const val TYPE_GROUP = 1
const val TYPE_PERSONAL = 2
const val TYPE_ADV = 3


var data = listOf(
    GroupItem("cars"),
    GroupItem("bicicles"),
    GroupItem("art"),
    AdvItem("https://www.google.com/favicon.ico"),
    GroupItem("food"),
    PersonalItem("Mary"),
    PersonalItem("Joe"),
    PersonalItem("Adam"),
    PersonalItem("Kelly")
)







abstract class OneItem {
    val id: Int = counter
    abstract val type: Int

    companion object {
        private var counter: Int = 0
            get() = field++
    }
}


data class AdvItem(val img: String): OneItem() {
    override val type = TYPE_ADV
}
data class GroupItem(val title: String, val img: String = "", val descr: String = ""): OneItem() {
    override val type = TYPE_GROUP
}
data class PersonalItem(val title: String): OneItem() {
    override val type = TYPE_PERSONAL
}