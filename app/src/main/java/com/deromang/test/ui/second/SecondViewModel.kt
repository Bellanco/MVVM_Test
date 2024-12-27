package com.deromang.test.ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.deromang.test.App
import com.deromang.test.R
import com.deromang.test.data.Repository
import com.deromang.test.data.Result
import com.deromang.test.data.db.FavoriteDAO
import com.deromang.test.di.AppDatabase
import com.deromang.test.model.DetailResponseModel
import com.deromang.test.model.Favorite
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SecondViewModel: ViewModel() {

    private val _getDetailResult = MutableLiveData<Result<DetailResponseModel>>()
    val getDetailResult: LiveData<Result<DetailResponseModel>> = _getDetailResult

    private val _isFavoriteResult = MutableLiveData<Result<Boolean>>()
    val isFavoriteResult: LiveData<Result<Boolean>> = _isFavoriteResult

    private val exception = CoroutineExceptionHandler { _, _ ->
        _getDetailResult.value = Result(error = R.string.label_error_request)
    }

    private var favoriteDao: FavoriteDAO? = null

    init {
        App.getContext()?.let { applicationContext ->
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database").build()
            favoriteDao = db.favoriteDao()
        }
    }

    fun getDetail() {

        val repository = Repository()

        viewModelScope.launch(exception) {

            withContext(Dispatchers.IO) {
                val responseModel = repository.getDetailElement()

                if (responseModel.isSuccessful) {
                    _getDetailResult.postValue(Result(success = responseModel.body()))
                } else {
                    _getDetailResult.value = Result(error = R.string.label_error_request)
                }
            }
        }
    }

    fun addFavorite(id: String) {
        viewModelScope.launch {
            try {
                val favorite = Favorite(id)
                favoriteDao?.insertFavorite(favorite)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            try {
                val favorite = Favorite(id)
                favoriteDao?.deleteFavorite(favorite)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun isFavorite(id: String) {
        viewModelScope.launch {
            try {
                val favorite = favoriteDao?.getFavoriteById(id)
                if (favorite != null) {
                    _isFavoriteResult.postValue(Result(success = true))
                } else {
                    _isFavoriteResult.postValue(Result(success = false))
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}