package com.harsh.rebelfoodsassignment.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.R
import com.harsh.rebelfoodsassignment.data.remote.response.AddressResponse
import com.harsh.rebelfoodsassignment.databinding.ActivityMapsBinding
import com.harsh.rebelfoodsassignment.di.component.DaggerActivityComponent
import com.harsh.rebelfoodsassignment.di.module.ActivityModule
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var viewModel: MapsViewModel

    lateinit var binding: ActivityMapsBinding

    private var addressResponse: AddressResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setupObservers()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        if (intent != null) {
            addressResponse = intent.getParcelableExtra("address")
            viewModel.addressResponse = addressResponse
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupObservers() {
        viewModel.onBackPressed.observe(this, Observer {
            finish()
        })
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val lat = addressResponse?.geo?.lat?.toDoubleOrNull()
        val lng = addressResponse?.geo?.lng?.toDoubleOrNull()
        if (lat != null && lng != null) {
            val sydney = LatLng(lat, lng)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in ${addressResponse?.city}"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }

    }
}
