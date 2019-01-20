package com.example.myapplication

const val TYPE_GROUP = 1
const val TYPE_PERSONAL = 2


val data = listOf(
    GroupItem("cars"),
    GroupItem("bicicles"),
    GroupItem("art"),
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


data class GroupItem(val title: String): OneItem() {
    override val type = TYPE_GROUP
}
data class PersonalItem(val title: String): OneItem() {
    override val type = TYPE_PERSONAL
}