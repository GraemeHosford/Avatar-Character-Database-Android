package graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview

import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

abstract class BasePaginatedRecyclerViewAdapter<UiModel : BaseUiModel, ViewHolder : BaseViewHolder<UiModel>> :
    BaseRecyclerViewAdapter<UiModel, ViewHolder>() {
    /* Forcing paginated items into a new list so ListAdapter will calculate the
    difference correctly for paginated items */
    override fun submitList(list: List<UiModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}