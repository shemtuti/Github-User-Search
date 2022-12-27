package com.example.github.user.network

import com.example.github.user.database.DatabaseFollower
import com.example.github.user.database.DatabaseFollowing
import com.example.github.user.database.DatabaseUser
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * A retrofit service to fetch a user
 */
interface GithubUserService {
    @GET("users/{user}")
    fun fetchUser(@Path("user")user: String): Deferred<DatabaseUser>

    @GET("users/{user}/followers")
    fun fetchFollowers(
        @Path("user") user: String): Deferred<List<DatabaseFollower>>

    @GET("users/{user}/following")
    fun fetchFollowing(
        @Path("user") user: String): Deferred<List<DatabaseFollowing>>

}


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Main entry point for network access
 */
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    val githubService = retrofit.create(GithubUserService::class.java)
}
