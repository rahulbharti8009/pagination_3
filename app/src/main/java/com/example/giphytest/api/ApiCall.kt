package com.example.giphytest.api

import com.example.giphytest.entity.MtResponseEntity
import com.example.giphytest.entity.response.GiphyEntity
import com.example.giphytest.pagination3.enity.ApiResponse
import com.example.giphytest.pagination3.enity.RedditPost
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiCall {

    @GET("trending")
    suspend fun getData(
        @Query("api_key") api_key: String,
        @Query("limit") limit: Int
    ): Deferred<GiphyEntity>

    @GET("api/users")
    suspend fun getListData(@Query("page") pageNumber: Int): Response<ApiResponse>


    @GET("/r/{subreddit}/hot.json")
    suspend fun getFetchData(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): ListingResponse

    class ListingResponse(val data: ListingData)
    class ListingData(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
    )

    data class RedditChildrenResponse(val data: RedditPost)


    @GET("/r/{subreddit}/hot.json")
    suspend fun getFetchData(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int,
    ): Response<ApiResponse>


    @POST("api/getReport")
    suspend fun getData2(@Header("accesskey") key: String, @Body data: JsonObject): MtResponseEntity

}