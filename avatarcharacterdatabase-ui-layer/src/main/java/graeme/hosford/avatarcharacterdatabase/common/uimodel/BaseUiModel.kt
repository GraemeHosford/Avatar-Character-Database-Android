package graeme.hosford.avatarcharacterdatabase.common.uimodel

abstract class BaseUiModel(
    val id: Long
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is BaseUiModel) {
            return false
        }

        return areContentsTheSame(other)
    }

    override fun hashCode(): Int = id.toInt()

    abstract fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean
}