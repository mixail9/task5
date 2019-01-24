package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception

class DataLoader(val ctx: Activity) {

    private val loadSteps = 2
    private var completedSteps = 0
    val newData = ArrayList<OneItem>()
    val advPariod = 5

    fun load(): Boolean {
        val queue = Volley.newRequestQueue(ctx)
        var descr: String

        queue.add(StringRequest(
            "https://api.vk.com/method/groups.getMembers?access_token=2c1d1ab72c1d1ab72c1d1ab7032c7500cc22c1d2c1d1ab7705d5f28d93d7589f0bea47c&v=5.92&group_id=64282323&count=20&fields=Name,photo_100,city,country,place,description,sex",
            Response.Listener<String> { response ->
                val responseJson = JSONObject(response).getJSONObject("response").getJSONArray("items")
                for(i in 0..responseJson.length()-1) {
                    with(responseJson.getJSONObject(i)) {
                        //if(i % advPariod == 0)
                        //    newData.add(AdvItem("https://sun9-22.userapi.com/c846522/v846522817/113130/3bdEJoWKoiY.jpg"))
                        descr = ""
                        try {
                            descr = /*"\nphone = " + this.getString("mobile_phone") + */"\nsex = " + if(this.getInt("sex")==2) "man" else "woman" + "\ncountry = " + this.getJSONObject("country").getString("title") + "\ncity = " + this.getJSONObject("city").getString("title")
                        } catch(e: Exception) { Log.d("current", e.toString()) }
                        newData.add(
                            GroupItem(
                                this.getString("first_name") + " " + this.getString("last_name"),
                                this.getString("photo_100"),
                                "id = " + this.getString("id") + descr
                            )
                        )
                    }
                }
                completedSteps++
                setLocal()

            },
            Response.ErrorListener { completedSteps++ }
        ))

        queue.add(StringRequest(
            "https://api.vk.com/method/wall.get?access_token=2c1d1ab72c1d1ab72c1d1ab7032c7500cc22c1d2c1d1ab7705d5f28d93d7589f0bea47c&v=5.92&owner_id=-64282323&count=20&extended=1&fields=from_id,text,likes",
            Response.Listener<String> { response ->
                Log.d("current", response)
                val responseJson = JSONObject(response).getJSONObject("response").getJSONArray("items")
                for(i in 0..responseJson.length()-1) {
                    if(i % advPariod == 0 && i > 0)
                        newData.add(AdvItem("https://sun9-22.userapi.com/c846522/v846522817/113130/3bdEJoWKoiY.jpg"))
                    with(responseJson.getJSONObject(i)) {
                        try {
                        newData.add(
                                PersonalItem(
                                    this.getString("date"),
                                    this.getString("text"),
                                    this.getJSONObject("likes").getInt("count")
                                )
                        )
                    } catch(e: Exception) { Log.e("current", e.toString())}
                    }
                }
                completedSteps++
                setLocal()

            },
            Response.ErrorListener { completedSteps++ }
        ))
        return true
    }


    fun setLocal() {
        Log.d("current", "step = " + completedSteps + " / " + loadSteps)
        if(completedSteps < loadSteps)
            return

        data = newData
        (ctx as Main2Activity).adapterObj.apply {
            items = newData
            notifyDataSetChanged()
        }
    }
}