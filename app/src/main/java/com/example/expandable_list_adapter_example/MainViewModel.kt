package com.example.expandable_list_adapter_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private var sectionList: List<Section> = listOf()

    val sectionListLiveData = MutableLiveData<List<Section>>(sectionList)

    val isRefreshing = MutableLiveData<Boolean>(false)

    fun refresh() {
        isRefreshing.value = true
        getSectionList()
    }

    fun getSectionList() {
        sectionList = fakeSectionList()
        sectionListLiveData.value = sectionList
        isRefreshing.value = false
    }

    fun expand(sectionId: Int) {
        sectionList = sectionList.map { section ->
            if (section.id == sectionId) {
                section.copy(
                    isExpandable = !section.isExpandable
                )
            } else {
                section.copy(
                    isExpandable = true
                )
            }
        }
        sectionListLiveData.value = sectionList
    }

    private fun fakeSectionList(): List<Section> {
        val sectionList: MutableList<Section> = mutableListOf()
        repeat(Random.nextInt(0, 100)) { index ->
            val section = Section(
                id = index,
                name = "Section $index",
                items = fakeSectionItemList(),
                isExpandable = true
            )
            sectionList.add(section)
        }
        return sectionList
    }

    private fun fakeSectionItemList(): List<Section.Item> {
        val sectionItemList: MutableList<Section.Item> = mutableListOf()
        repeat(Random.nextInt(0, 10)) { index ->
            val sectionItem = Section.Item(
                id = index,
                name = "Item $index"
            )
            sectionItemList.add(sectionItem)
        }
        return sectionItemList
    }
}