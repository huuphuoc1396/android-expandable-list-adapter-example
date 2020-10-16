package com.example.expandable_list_adapter_example

import androidx.recyclerview.widget.RecyclerView
import com.example.expandable_list_adapter_example.databinding.ItemSectionItemBinding

class SectionItemViewHolder(
    private val binding: ItemSectionItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(sectionItem: Section.Item) {
        binding.sectionItem = sectionItem
    }
}