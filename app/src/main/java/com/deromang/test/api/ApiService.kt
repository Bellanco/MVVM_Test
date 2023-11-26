package com.deromang.test.api

import com.deromang.test.model.CharactersResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api")
    suspend fun getCharacters(
        @Query("gender") gender: String?,
        @Query("results") results: Int = 45
    ): Response<CharactersResponseModel>

}

