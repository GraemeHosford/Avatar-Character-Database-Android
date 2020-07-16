package graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState

abstract class BaseViewModel<Result> : ViewModel() {
    private val errorMutable = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean>
        get() = errorMutable

    private val loadingMutable = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutable

    private val completedMutable = MutableLiveData<Boolean>()
    val completedLiveData: LiveData<Boolean>
        get() = completedMutable

    protected suspend fun handleRepoStateResult(repoState: RepoState<Result>) {
        when (repoState) {
            is RepoState.Completed -> doOnCompletedResult(repoState.data)
            is RepoState.Error -> doOnErrorResult()
            is RepoState.Loading -> doOnLoading()
        }
    }

    @CallSuper
    protected open suspend fun doOnCompletedResult(result: Result) {
        errorMutable.value = false
        loadingMutable.value = false
        completedMutable.value = true
    }

    protected open suspend fun doOnErrorResult() {
        errorMutable.value = true
        completedMutable.value = false
        loadingMutable.value = false
    }

    protected open suspend fun doOnLoading() {
        loadingMutable.value = true
        completedMutable.value = false
        errorMutable.value = false
    }
}