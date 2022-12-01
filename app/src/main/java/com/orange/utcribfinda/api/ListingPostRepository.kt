package com.orange.utcribfinda.api

import android.util.Log

class ListingPostRepository(private val apartmentApi: ApartmentApi) {

    private fun unpackPosts(response: ApartmentApi.ListingResponse): List<ListingPost> {
        // XXX Write me.
        val result = mutableListOf<ListingPost>()
        val children = response.data.output
        Log.d("rESD", children.toString())
        for (element in children) {
            //Log.d("eVAl", element.data.sqFT.toString())
            result.add(element)
        }
        //Log.d("r", result.toString())
        return result

    }

    suspend fun getPosts(): List<ListingPost> {
        // XXX Write me.
        val response = apartmentApi.getVillasOnRioPosts()
        Log.d("Responses", response.data.toString())
        return unpackPosts(response)
    }
}