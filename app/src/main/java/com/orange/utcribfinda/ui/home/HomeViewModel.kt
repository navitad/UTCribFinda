package com.orange.utcribfinda.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.utcribfinda.api.ApartmentApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.orange.utcribfinda.api.ListingPost
import com.orange.utcribfinda.api.ListingPostRepository

class HomeViewModel : ViewModel() {



    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


}