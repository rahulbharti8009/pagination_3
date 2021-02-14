package com.example.giphytest.api

import javax.inject.Inject

class AuthRepository @Inject constructor() {

    @JvmField
    @Inject
    var apiCall: ApiCall? = null

    suspend fun getData() =  apiCall!!.getData("g4r6jg0XscoaFouQf2GGlTdTPEMSpm7G", 5).await()

}