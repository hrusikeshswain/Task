package com.example.androidtest.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidtest.data.model.AllDataResponse


import com.example.androidtest.data.repository.MainRepository
import com.example.androidtest.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val users = MutableLiveData<Resource<AllDataResponse>>()
   /* private val compositeDisposable = CompositeDisposable()

    init (){
        fetchUsers()
    }

    private suspend fun fetchUsers() {
        users.postValue(Resource.loading(null))
        mainRepository.getHomeData(PostBookingId(""))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ userList ->
                users.postValue(Resource.success(userList))
            }, { throwable ->
                users.postValue(Resource.error("Something Went Wrong", null))
            })?.let {
                compositeDisposable.add(
                    it
            )
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }*/

    fun getHomeData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getHomeData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = "Error Occurred!"))
        }
    }

    /*fun getHomeData(postbookingid: PostBookingId): LiveData<Resource<AllDataResponse>> {
        return users
    }*/

}