package graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.pagination

import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder

abstract class BasePaginatedRecyclerViewAdapter<UiModel : BaseUiModel, ViewHolder : BaseViewHolder<UiModel>> :
    BaseRecyclerViewAdapter<UiModel, ViewHolder>() {
    /* Forcing paginated items into a new list so ListAdapter will calculate the
    difference correctly for paginated items */
    override fun submitList(list: List<UiModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}