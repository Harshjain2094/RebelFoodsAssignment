package com.harsh.rebelfoodsassignment.ui.users

import androidx.recyclerview.widget.RecyclerView
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import com.harsh.rebelfoodsassignment.databinding.UsersItemViewLayoutBinding
import com.harsh.rebelfoodsassignment.di.component.DaggerViewHolderComponent
import com.harsh.rebelfoodsassignment.di.module.ViewHolderModule
import javax.inject.Inject

class UsersItemViewHolder(private val binding: UsersItemViewLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    @Inject
    lateinit var viewModel: UsersItemViewModel

    init {
        DaggerViewHolderComponent.builder()
            .applicationComponent((binding.root.context.applicationContext as MyApplication).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()
            .inject(this)

        viewModel.context = binding.root.context
    }

    fun bind(data: UsersEntity) {
        viewModel.updateData(data)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}