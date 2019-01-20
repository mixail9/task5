package com.example.myapplication

const val TYPE_GROUP = 1
const val TYPE_PERSONAL = 2
const val TYPE_ADV = 3


val data = listOf(
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
data class GroupItem(val title: String): OneItem() {
    override val type = TYPE_GROUP
}
data class PersonalItem(val title: String): OneItem() {
    override val type = TYPE_PERSONAL
}