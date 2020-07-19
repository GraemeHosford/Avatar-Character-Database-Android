package graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel

import androidx.annotation.CallSuper
import graeme.hosford.avatarcharacterdatabase.repo.common.PaginatedRepo
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import kotlinx.coroutines.flow.collect

abstract class BasePaginatedViewModel<Result, Repo : PaginatedRepo<Result>>(
    private val repo: Repo
) : BaseViewModel<Result>() {
    suspend fun requestNextPage() {
        repo.getNextPage().collect {
            handlePaginatedRepoStateResult(it)
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