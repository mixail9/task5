package com.example.myapplication

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flipboard.bottomsheet.BottomSheetLayout
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.squareup.picasso.Picasso


class GroupHolder(val view: View): RecyclerView.ViewHolder(view)

class MainAdapter(var ctx: Activity, val dataSet: List<OneItem>): ListDelegationAdapter<List<OneItem>>() {

    init{
        delegatesManager.addDelegate(GroupAdapter(ctx)).addDelegate(PersonalAdapter(ctx)).addDelegate(AdvAdapter(ctx))
        setItems(dataSet)
        //Log.d("current", dataSet.toString())
    }
}



class AdvAdapter(val ctx: Activity): AdapterDelegate<List<OneItem>>() {


    override fun isForViewType(items: List<OneItem>, position: Int): Boolean {
        return (items.get(position) is AdvItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GroupHolder(ctx.layoutInflater.inflate(R.layout.adv_item, parent, false))
    }

    override fun onBindViewHolder(
        items: List<OneItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {

        val item = items.get(position) as AdvItem
        val imageView = (holder as GroupHolder).view.findViewById<ImageView>(R.id.imageView)
        //(holder as GroupHolder).view.findViewById<ImageView>(R.id.imageView).setImageResource(item.img)
        val url = "your_url"
        Picasso.get().load(item.img)
            .placeholder(R.drawable.linux)
            .error(R.drawable.linux).into(imageView)
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
        (holder as GroupHolder).view.apply {
            findViewById<TextView>(R.id.textView).text = item.title
            Picasso.get().load(item.img)
                .placeholder(R.drawable.linux)
                .error(R.drawable.linux).into(findViewById<ImageView>(R.id.imageView))
            findViewById<TextView>(R.id.textView2).text = item.descr
        }
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
        val item = items.get(position) as PersonalItem
        with((holder as GroupHolder).view) {
            findViewById<TextView>(R.id.textView).text = item.title
            findViewById<TextView>(R.id.textView2).text = item.text?.substring(0..150)
            if(item.likes > 3) {
                findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.heart2)
                findViewById<TextView>(R.id.textView).text = item.likes.toString()
            } else {
                findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.heart)
                findViewById<TextView>(R.id.textView).text = ""
            }
            setOnClickListener{
                val popupNode = LayoutInflater.from(ctx).inflate(R.layout.popup, ctx.findViewById(R.id.imageView), false)
                popupNode.findViewById<TextView>(R.id.fullText).text = item.text
                ctx.findViewById<BottomSheetLayout>(R.id.popupWrap).showWithSheetView(popupNode)

            }
        }
    }
}

