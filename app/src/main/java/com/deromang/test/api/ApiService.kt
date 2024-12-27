package com.deromang.test.api

import com.deromang.test.model.DetailResponseModel
import com.deromang.test.model.ListResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("list.json")
    suspend fun getListElements(
    ): Response<MutableList<ListResponseModel>>

    @GET("detail.json")
    suspend fun getDetail(
    ): Response<DetailResponseModel>

}

