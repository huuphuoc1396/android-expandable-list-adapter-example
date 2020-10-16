package com.example.expandable_list_adapter_sample

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expandable_list_adapter_sample.databinding.ItemSectionBinding

class SectionViewHolder(
    private val binding: ItemSectionBinding,
    private val onSectionClickListener: (Section) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.section?.let {
                onSectionClickListener(it)
            }
        }
    }

    fun bind(section: Section) {
        binding.section = section
    }

    object Binding {

        @JvmStatic
        @BindingAdapter("sectionExpandable")
        fun setSectionExpandable(imageView: AppCompatImageView, isExpandable: Boolean) {
            if (isExpandable) {
                imageView.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                imageView.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }
    }
}