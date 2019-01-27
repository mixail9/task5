package com.example.myapplication


const val TYPE_GROUP = 1
const val TYPE_PERSONAL = 2
const val TYPE_ADV = 3


var data: ArrayList<OneItem> = ArrayList(listOf(
    GroupItem("cars"),
    GroupItem("bicicles"),
    GroupItem("art"),
    AdvItem("https://www.google.com/favicon.ico"),
    GroupItem("food"),
    PersonalItem("Mary"),
    PersonalItem("Joe"),
    PersonalItem("Adam"),
    PersonalItem("Kelly")
))


const val PREVIEW_STRING_LENGTH = 150


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
data class PersonalItem(val title: String, val text: String? = null, val likes: Int = 0): OneItem() {
    override val type = TYPE_PERSONAL
}