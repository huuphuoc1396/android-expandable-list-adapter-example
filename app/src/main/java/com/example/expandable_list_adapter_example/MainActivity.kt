package com.example.expandable_list_adapter_example

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.expandable_list_adapter_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        binding.recyclerView.adapter = MainListAdapter(
            onSectionClickListener = { section ->
                viewModel.expand(section.id)
            }
        )

        viewModel.getSectionList()
    }

    object Binding {

        @JvmStatic
        @BindingAdapter("sectionList")
        fun setSectionList(recyclerView: RecyclerView, sectionList: List<Section>) {
            val list = mutableListOf<Any>()
            val mainListAdapter = recyclerView.adapter as? MainListAdapter

            sectionList.forEach { section ->
                list.add(section)
                if (!section.isExpandable) {
                    list.addAll(section.items)
                }
            }

            mainListAdapter?.submitList(list)
        }
    }
}