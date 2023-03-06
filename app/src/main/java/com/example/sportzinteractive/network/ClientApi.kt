package com.example.sportzinteractive.network

import com.example.sportzinteractive.constants.WebConstants
import com.example.sportzinteractive.network.model.ResponseData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ClientApi {

    @GET(WebConstants.API2)
    fun getMatchData(): Single<ResponseData>
}