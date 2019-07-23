package com.harsh.rebelfoodsassignment.di.component

import com.harsh.rebelfoodsassignment.di.ViewModelScope
import com.harsh.rebelfoodsassignment.di.module.ViewHolderModule
import com.harsh.rebelfoodsassignment.ui.users.UsersItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(holder: UsersItemViewHolder)
}