package com.deromang.test.data

import com.deromang.test.api.ApiGenerator
import com.deromang.test.model.DetailResponseModel
import com.deromang.test.model.ListResponseModel
import retrofit2.Response

class Repository {

    suspend fun getListElements(): Response<MutableList<ListResponseModel>> =
        ApiGenerator.createService().getListElements()

    suspend fun getDetailElement(): Response<DetailResponseModel> =
        ApiGenerator.createService().getDetail()


}