package com.example.githubapitest.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.githubapitest.model.Item
import com.example.githubapitest.repository.local.GithubApiDao
import com.example.githubapitest.repository.repo.GithubApiRepository
import com.example.punchandroidtest.extension.NetworkManager
import com.example.punchandroidtest.extension.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubApiViewModel(
    private val repository: GithubApiRepository,
    private val dao: GithubApiDao,
    private val networkManager: NetworkManager,
    private val context: Context
) : ViewModel() {

    private val _githubApi = MutableLiveData<Resource<List<Item>>>()
    val observeGithubApi: LiveData<Resource<List<Item>>>
        get() = _githubApi

    val getCachedApi
        get() = Transformations.map(dao.fetchLocalData()) {
            it
        }

    fun init() {
        getGithubAPI()
    }

    private fun getGithubAPI(
        page: Int = 1
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _githubApi.postValue(Resource.loading(null))
                if (networkManager.isConnected(context)) {
                    repository.getGithubApi(page).let {
                        if (it.isSuccessful) {
                            _githubApi.postValue(Resource.success(it.body()?.items))
                        } else {
                            when (it.code()) {
                                404 -> _githubApi.postValue(Resource.error("not found", null))
                                500 -> _githubApi.postValue(Resource.error("server broken", null))

                                else -> _githubApi.postValue(Resource.error(it.errorBody().toString(), null))
                            }
                        }
                    }
                } else {
                    _githubApi.postValue(Resource.error("No Internet connection", null))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
