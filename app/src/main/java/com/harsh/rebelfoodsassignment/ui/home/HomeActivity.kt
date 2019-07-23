package com.harsh.rebelfoodsassignment.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.R
import com.harsh.rebelfoodsassignment.databinding.ActivityMainBinding
import com.harsh.rebelfoodsassignment.di.component.DaggerActivityComponent
import com.harsh.rebelfoodsassignment.di.module.ActivityModule
import com.harsh.rebelfoodsassignment.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var userPagerAdapter: UserPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupObservers()
        setupViews()
    }

    private fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupViews() {
        binding.viewPager.adapter = userPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.reset -> {
                viewModel.reset()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
