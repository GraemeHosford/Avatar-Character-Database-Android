package graeme.hosford.avatarcharacterdatabase.common.view.viewmodel.pagination

import androidx.annotation.CallSuper
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.common.view.viewmodel.BaseViewModel
import graeme.hosford.avatarcharacterdatabase.common.view.viewmodel.ViewModelResult
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.RepoState
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.pagination.PaginatedRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BasePaginatedViewModel<Result, Repo : PaginatedRepo<Result>>(
    private val repo: Repo
) : BaseViewModel<Result>() {
    fun requestNextPage() {
        viewModelScope.launch {
            repo.getNextPage().collect {
                handlePaginatedRepoStateResult(it)
            }
        }
    }

    protected suspend fun handlePaginatedRepoStateResult(result: RepoState<List<Result>>) {
        when (result) {
            is RepoState.Error -> doOnErrorResult()
            is RepoState.Loading -> doOnLoading()
            is RepoState.Completed -> doOnPaginatedResult(result.data)
        }
    }

    @CallSuper
    protected open suspend fun doOnPaginatedResult(result: List<Result>) {
        resultMutable.value = ViewModelResult.COMPLETED
    }
}