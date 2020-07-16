package graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState

abstract class BaseViewModel<Result> : ViewModel() {
    protected val errorMutable = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean>
        get() = errorMutable

    protected suspend fun handleRepoStateResult(repoState: RepoState<Result>) {
        when (repoState) {
            is RepoState.Completed -> {
                doOnCompletedResult(repoState.data)
            }
            is RepoState.Error -> doOnErrorResult()
        }
    }

    @CallSuper
    protected open suspend fun doOnCompletedResult(result: Result) {
        errorMutable.value = false
    }

    protected open suspend fun doOnErrorResult() {
        errorMutable.value = true
    }
}