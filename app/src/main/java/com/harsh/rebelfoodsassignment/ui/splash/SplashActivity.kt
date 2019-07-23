package com.harsh.rebelfoodsassignment.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.R
import com.harsh.rebelfoodsassignment.databinding.ActivitySplashBinding
import com.harsh.rebelfoodsassignment.di.component.DaggerActivityComponent
import com.harsh.rebelfoodsassignment.di.module.ActivityModule
import com.harsh.rebelfoodsassignment.ui.home.HomeActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupObservers()
        viewModel.onViewCreated()
    }

    private fun setupObservers() {
        viewModel.launchHome.observe(this, Observer {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        })

        viewModel.messageString.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}
