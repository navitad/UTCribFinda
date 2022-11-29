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
    private var netPosts = mutableListOf<ListingPost>()
    private var favoritesList = mutableListOf<ListingPost>()

    private var netPostsString = MutableLiveData<String>()



    init {
        // XXX one-liner to kick off the app
        //setTitleToSubreddit()
        //repoFetch()
        postsFetch()
        //subredditsFetch()
    }


    fun postsFetch() {
        fetchDone.postValue(false)
        viewModelScope.launch(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            //var posts = ListingPostRepository(api).getPosts()
            netPosts = ListingPostRepository(api).getPosts() as MutableList<ListingPost>
//            if(posts == null) {
//                netPostsString.postValue("didn't work lols")
//            } else {
                //netPosts.postValue(ListingPostRepository(api).getPosts())
                //netPostsString.postValue(ListingPostRepository(api).getPosts()
            //}

            //netPosts.postValue(ListingPostRepository(api).getPosts())
        }
    }


    fun observeNetPosts() : MutableList<ListingPost> {
        return netPosts
    }

    // Looks pointless, but if LiveData is set up properly, it will fetch posts
    // from the network
//    fun repoFetch() {
//        val fetch = subreddit.value
//        subreddit.value = fetch
//    }

//    fun observeFloorPlan(): LiveData<String> {
//        return floorPlan
//    }
//    fun setTitle(newTitle: String) {
//        floorPlan.value = newTitle
//    }
    // The parsimonious among you will find that you can call this in exactly two places
//    fun setTitleToSubreddit() {
//        title.value = "r/${subreddit.value}"
//    }

//    fun setSubreddit(curSubreddit : String) {
//        subreddit.value = curSubreddit
//    }

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