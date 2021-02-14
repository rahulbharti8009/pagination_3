package com.example.giphytest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.giphytest.api.ApiCall
import com.example.giphytest.api.AuthRepository
import com.example.giphytest.api.Resource
import com.example.giphytest.pagination3.PostDataSource
import com.example.giphytest.pagination3.enity.Children
import com.example.giphytest.pagination3.enity.Data
import com.example.giphytest.pagination3.enity.RedditPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class MyViewModel  @ViewModelInject constructor(var authRepository: AuthRepository, var apiCall: ApiCall) : ViewModel() {

    fun getData() =  liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            emit(Resource.success(data = authRepository.getData()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    val listData : Flow<PagingData<RedditPost>> = Pager(PagingConfig(pageSize = 60)) {
        PostDataSource(apiCall)
    }.flow.cachedIn(viewModelScope)

}