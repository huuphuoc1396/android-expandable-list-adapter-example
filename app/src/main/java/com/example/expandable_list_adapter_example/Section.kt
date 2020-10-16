package com.example.expandable_list_adapter_example

data class Section(
    val id: Int,
    val name: String,
    val isExpandable: Boolean,
    val items: List<Item>
) {
    data class Item(
        val id: Int,
        val name: String
    )
}