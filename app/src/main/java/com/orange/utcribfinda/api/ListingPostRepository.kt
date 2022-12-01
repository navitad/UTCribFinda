package com.orange.utcribfinda.api

import android.text.SpannableString
import android.util.Log
import com.google.gson.GsonBuilder
import com.orange.utcribfinda.MainActivity
import com.orange.utcribfinda.api.ListingPost
import kotlinx.coroutines.handleCoroutineException


class ListingPostRepository(private val apartmentApi: ApartmentApi) {
    // NB: This is for our testing.
//    val gson = GsonBuilder().registerTypeAdapter(
//        SpannableString::class.java, ApartmentApi.SpannableDeserializer()
//    ).create()

    private fun unpackPosts(response: ApartmentApi.ListingResponse): List<ListingPost> {
        // XXX Write me.
        val result = mutableListOf<ListingPost>()
        val children = response.data.output
        for(element in children) {
            result.add(element.data)
        }
        return result

    }

    //List<ListingPost>
    suspend fun getPosts(): List<ListingPost> {
        // XXX Write me.
        val response = apartmentApi.getVillasOnRioPosts()
        Log.d("r", response.data.toString())
        return unpackPosts(response)
    }

//    suspend fun getSubreddits(): List<ListingPost> {
//        if (MainActivity.globalDebug) {
//            val response = gson.fromJson(
//                MainActivity.subreddit1,
//                ApartmentApi.ListingResponse::class.java)
//            return unpackPosts(response)
//        } else {
//            // XXX Write me.
//            val response = ApartmentApi.getSubreddit()
//            return unpackPosts(response)
//        }
//    }
}