package com.example.myapplication

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class GroupHolder(val view: View): RecyclerView.ViewHolder(view)

class MainAdapter(var ctx: Activity, var dataSet: List<OneItem>): ListDelegationAdapter<List<OneItem>>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position < 2)
            setItems(dataSet)

        super.onBindViewHolder(holder, position)
    }

    init{
        delegatesManager.addDelegate(GroupAdapter(ctx)).addDelegate(PersonalAdapter(ctx))
        setItems(dataSet)
        Log.d("current", dataSet.toString())
    }
}



class GroupAdapter(val ctx: Activity): AdapterDelegate<List<OneItem>>() {


    override fun isForViewType(items: List<OneItem>, position: Int): Boolean {
        return (items.get(position) is GroupItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GroupHolder(ctx.layoutInflater.inflate(R.layout.group_item, parent, false))
    }

    override fun onBindViewHolder(
        items: List<OneItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {

        val item = items.get(position) as GroupItem
        (holder as GroupHolder).view.findViewById<TextView>(R.id.textView).text = item.title
    }
}

class PersonalAdapter(val ctx: Activity): AdapterDelegate<List<OneItem>>() {


    override fun isForViewType(items: List<OneItem>, position: Int): Boolean {
        return (items.get(position) is PersonalItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GroupHolder(ctx.layoutInflater.inflate(R.layout.personal_item, parent, false))
    }

    override fun onBindViewHolder(
        items: List<OneItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {

        Log.d("current", "onBindViewHolder" + items.toString())
        val item = items.get(position) as PersonalItem
        (holder as GroupHolder).view.findViewById<TextView>(R.id.textView).text = item.title
    }
}

