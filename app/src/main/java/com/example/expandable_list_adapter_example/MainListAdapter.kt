package com.example.expandable_list_adapter_example

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expandable_list_adapter_example.databinding.ItemSectionBinding
import com.example.expandable_list_adapter_example.databinding.ItemSectionItemBinding

class MainListAdapter(
    private val onSectionClickListener: (Section) -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ViewType.SECTION -> {
                val itemSectionBinding = ItemSectionBinding.inflate(inflater, parent, false)
                SectionViewHolder(itemSectionBinding, onSectionClickListener)
            }
            ViewType.SECTION_ITEM -> {
                val itemSectionItemBinding = ItemSectionItemBinding.inflate(inflater, parent, false)
                SectionItemViewHolder(itemSectionItemBinding)
            }

            else -> throw(Throwable("View type not matching"))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is Section -> (holder as? SectionViewHolder)?.bind(item)
            is Section.Item -> (holder as? SectionItemViewHolder)?.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Section -> {
                ViewType.SECTION
            }
            is Section.Item -> {
                ViewType.SECTION_ITEM
            }
            else -> super.getItemViewType(position)
        }
    }

    object ViewType {
        const val SECTION = 101
        const val SECTION_ITEM = 102
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {

            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return when {
                    oldItem is Section && newItem is Section -> {
                        oldItem.id == newItem.id
                    }
                    oldItem is Section.Item && newItem is Section.Item -> {
                        oldItem.id == newItem.id
                    }
                    else -> false
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return when {
                    oldItem is Section && newItem is Section -> {
                        oldItem == newItem
                    }
                    oldItem is Section.Item && newItem is Section.Item -> {
                        oldItem == newItem
                    }
                    else -> false
                }
            }

        }
    }
}