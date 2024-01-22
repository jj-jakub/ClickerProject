package com.jj.clickerproject.data.google.service

import retrofit2.Response
import retrofit2.http.GET

interface ClickerService {
    @GET("/")
    suspend fun getGoogleData(): Response<Unit>
}