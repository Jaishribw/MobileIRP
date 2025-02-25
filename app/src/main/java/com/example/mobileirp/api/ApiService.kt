package com.example.mobileirp.api

import com.example.mobileirp.model.MyRowItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("region/europe")
    suspend fun getCountries(): Response<MyRowItem>
}