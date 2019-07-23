package com.harsh.rebelfoodsassignment.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harsh.rebelfoodsassignment.data.remote.response.AddressResponse

class MapsViewModel : ViewModel() {

    var addressResponse: AddressResponse? = null
    val onBackPressed: MutableLiveData<String> = MutableLiveData()

    fun getAddress() =
        addressResponse?.let {
            return@let "Address: ${it.suite}, ${it.street}, ${it.city} - ${it.zipcode}"
        }

    fun onBackClicked() {
        onBackPressed.postValue("")
    }
}