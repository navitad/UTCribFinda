package com.orange.utcribfinda.api

import android.text.SpannableString
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.orange.utcribfinda.api.ListingPost
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Type

interface ApartmentApi {
    //https://wrapapi.com
    @GET("/use/navita/apartments/villasOnRioListings/0.0.1?wrapAPIKey=MVBCXaViCKCM9UnqmwuUo3d0R9fTftzN")
    suspend fun getVillasOnRioPosts() : ListingResponse

    data class ListingResponse(val output: List<List<ListingPost>>)

    //data class ListingResponse(val output: List<ListingPost>)

//    class ListingResponse(val output: ListingData)
//
//    class ListingData(
//        val children: List<ListingChildrenResponse>,
//        val after: String?,
//        val before: String?
//    )
//    data class ListingChildrenResponse(val output: ListingPost)

    class SpannableDeserializer : JsonDeserializer<SpannableString> {
        // @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): SpannableString {
            return SpannableString(json.asString)
        }
    }

    companion object {
        // Leave this as a simple, base URL.  That way, we can have many different
        // functions (above) that access different "paths" on this server
        // https://square.github.io/okhttp/4.x/okhttp/okhttp3/-http-url/
        var url = HttpUrl.Builder()
            .scheme("https")
            .host("wrapapi.com")
            .build()

        // Public create function that ties together building the base
        // URL and the private create function that initializes Retrofit
        fun create(): ApartmentApi = create(url)
        private fun create(httpUrl: HttpUrl): ApartmentApi {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApartmentApi::class.java)
        }
    }
}