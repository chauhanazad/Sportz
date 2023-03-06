package com.example.sportzinteractive.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportzinteractive.MyApplication
import com.example.sportzinteractive.network.MyRetrofit
import com.example.sportzinteractive.network.model.ResponseData
import com.example.sportzinteractive.ui.base.UiState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SharedViewModel : ViewModel() {

    val responseData: MutableLiveData<UiState<ResponseData>> = MutableLiveData()
    private val service = MyRetrofit.getLocalApiClient(MyApplication.getInstance())

    init {
        getData()
    }

    @SuppressLint("CheckResult")
    private fun getData() {

        service?.getMatchData()
            ?.doOnSubscribe {
                responseData.postValue(UiState.Loading)
            }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                responseData.postValue(UiState.Success(it))
                Log.d("ResponseClient", it.toString())
            }){
                responseData.postValue(UiState.Error(it.message!!))
                Log.d("ResponseClient", it.toString())
            }
    }
}
