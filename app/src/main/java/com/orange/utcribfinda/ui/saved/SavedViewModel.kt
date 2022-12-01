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
    var fetchDone : MutableLiveData<Boolean> = MutableLiveData(false)

    //private var subredditList = mutableListOf<ListingPost>()
    //private var netPosts = MutableLiveData<List<ListingPost>>()
    private var netPosts = MutableLiveData<List<ListingPost>>()
    private var favoritesList = mutableListOf<ListingPost>()




    init {
        // XXX one-liner to kick off the app
        //setTitleToSubreddit()
        //repoFetch()
        fetchPosts()
        //subredditsFetch()
    }

    fun fetchPosts() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            netPosts.postValue(ListingPostRepository(api).getPosts())
        }
    }


    fun observeNetPosts() : MutableLiveData<List<ListingPost>> {
        return netPosts
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