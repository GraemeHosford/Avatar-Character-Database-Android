package graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.RepoState

abstract class BaseViewModel<Result> : ViewModel() {
    protected val resultMutable = MutableLiveData<ViewModelResult>()
    val resultLiveData: LiveData<ViewModelResult>
        get() = resultMutable

    protected suspend fun handleRepoStateResult(repoState: RepoState<Result>) {
        when (repoState) {
            is RepoState.Completed -> doOnCompletedResult(repoState.data)
            is RepoState.Error -> doOnErrorResult()
            is RepoState.Loading -> doOnLoading()
        }
    }

    @CallSuper
    protected open suspend fun doOnCompletedResult(result: Result) {
        resultMutable.value = ViewModelResult.COMPLETED
    }

    @CallSuper
    protected open suspend fun doOnErrorResult() {
        resultMutable.value = ViewModelResult.ERROR
    }

    @CallSuper
    protected open suspend fun doOnLoading() {
        resultMutable.value = ViewModelResult.LOADING
    }
}