package com.harsh.rebelfoodsassignment.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.harsh.rebelfoodsassignment.ui.favourites.FavoritesFragment
import com.harsh.rebelfoodsassignment.ui.users.UsersFragment

class UserPagerAdapter(private val context: Context, private val manager: FragmentManager) :
    FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                UsersFragment.newInstance()
            }

            1 -> {
                FavoritesFragment.newInstance()
            }

            else -> {
                UsersFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Users"
            }

            1 -> {
                "Favourites"
            }

            else -> {
                ""
            }
        }
    }
}