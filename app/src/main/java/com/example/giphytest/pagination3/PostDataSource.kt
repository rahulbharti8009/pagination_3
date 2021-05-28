package com.example.giphytest.pagination3


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphytest.api.ApiCall
import com.example.giphytest.pagination3.enity.RedditPost
import retrofit2.HttpException
import java.io.IOException

class PostDataSource(var apiCall : ApiCall,var subredditName :String): PagingSource<String, RedditPost>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return  try {
            val page = params.key ?: 1
            Log.e("TAG", "key = $page , Loadsize ${params.loadSize} , search = ${subredditName}")
            val items = apiCall.getFetchData(
                subreddit = subredditName,
                limit = params.loadSize,
                before = if (params is LoadParams.Prepend) params.key else null,
                after = if (params is LoadParams.Append) params.key else null
            ).data.children.map { it.data }

            LoadResult.Page(
                data = items,
                prevKey = items.firstOrNull()?.name,
                nextKey = items.lastOrNull()?.name
            )
//var response = apiCall.getFetchData("", params.loadSize)
//            val responseData = mutableListOf<Children>()
//            val data = response.body()?.data?.children ?: emptyList()
//            responseData.addAll(data)

//            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

//            return LoadResult.Page(
//                data = items,
//                prevKey = prevKey,
//                nextKey = currentLoadingPageKey.plus(10)
//            )
        }catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
//
//     override fun getRefreshKey(state: PagingState<Int, Children>): Int? {
//         TODO("Not yet implemented")
//     }

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.name
        }
    }
}