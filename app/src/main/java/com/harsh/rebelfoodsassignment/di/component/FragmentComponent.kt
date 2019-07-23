package com.harsh.rebelfoodsassignment.di.component

import com.harsh.rebelfoodsassignment.di.FragmentScope
import com.harsh.rebelfoodsassignment.di.module.FragmentModule
import com.harsh.rebelfoodsassignment.ui.favourites.FavoritesFragment
import com.harsh.rebelfoodsassignment.ui.users.UsersFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: UsersFragment)

    fun inject(fragment: FavoritesFragment)
}