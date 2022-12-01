package com.orange.utcribfinda.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.utcribfinda.api.ApartmentApi
import com.orange.utcribfinda.api.ListingPost
import com.orange.utcribfinda.api.ListingPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel : ViewModel() {

    var api = ApartmentApi.create()

    private var netPosts = MutableLiveData<List<ListingPost>>()
    private var favoritesList = mutableListOf<ListingPost>()
    var selectedPosts = mutableListOf<ListingPost>()
    private var postsPicked = MutableLiveData<List<ListingPost>>()



    init {
        // XXX one-liner to kick off the app
        fetchPosts()
    }

    private fun fetchPosts() : Boolean {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            netPosts.postValue(ListingPostRepository(api).getPosts())
        }
        return true
    }

    fun observePostsPicked(): MutableLiveData<List<ListingPost>> {
        return postsPicked
    }

    fun fetchSelected() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            postsPicked.postValue(selectedPosts)
        }
    }

    fun observeNetPosts() : MutableLiveData<List<ListingPost>> {
        return netPosts
    }

    fun observeSelectedPosts(): MutableList<ListingPost> {
        return selectedPosts
    }

    fun submitList(list: MutableList<ListingPost>){
        selectedPosts.clear()
        selectedPosts.addAll(list)
    }
    // XXX Write me, set, observe, deal with favorites
    fun observeFavoritesList() : MutableList<ListingPost> {
        return favoritesList
    }

    fun setFavoritesList(item : ListingPost) {
        favoritesList.add(item)
    }

    fun removeFavorite(item : ListingPost) {
        favoritesList.remove(item)
    }
}