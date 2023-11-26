package com.deromang.test.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deromang.test.R
import com.deromang.test.data.Repository
import com.deromang.test.data.Result
import com.deromang.test.model.CharactersResponseModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstViewModel : ViewModel() {

    private val _getCharacterResult = MutableLiveData<Result<CharactersResponseModel>>()
    val getCharacterResult: LiveData<Result<CharactersResponseModel>> = _getCharacterResult


    private val exception = CoroutineExceptionHandler { _, _ ->
        _getCharacterResult.value = Result(error = R.string.label_error_request)
    }

    fun getCharacters(gender: String? = null) {

        val repository = Repository()

        viewModelScope.launch(exception) {

            withContext(Dispatchers.IO) {
                val responseModel = repository.getCharacters(gender)

                if (responseModel.isSuccessful) {
                    _getCharacterResult.postValue(Result(success = responseModel.body()))
                } else {
                    _getCharacterResult.value = Result(error = R.string.label_error_request)
                }
            }
        }
    }

}