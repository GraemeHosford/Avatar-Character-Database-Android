package graeme.hosford.avatarcharacterdatabase.repo.common

sealed class RepoState<Result> {

    data class Completed<Result>(val data: Result) : RepoState<Result>()
    class Error<Result> : RepoState<Result>()

    companion object {
        fun <Result> completed(data: Result) = Completed(data)

        fun <Result> error() = Error<Result>()
    }
}