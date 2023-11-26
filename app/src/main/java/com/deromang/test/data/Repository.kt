package com.deromang.test.data

import com.deromang.test.api.ApiGenerator
import com.deromang.test.model.CharactersResponseModel
import retrofit2.Response

class Repository {
    suspend fun getCharacters(gender: String?): Response<CharactersResponseModel> =
        ApiGenerator.createService().getCharacters(gender)


}