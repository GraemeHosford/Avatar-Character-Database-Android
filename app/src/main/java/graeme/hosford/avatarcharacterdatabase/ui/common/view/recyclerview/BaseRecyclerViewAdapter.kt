package graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview

import androidx.recyclerview.widget.ListAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

abstract class BaseRecyclerViewAdapter<UiModel : BaseUiModel, ViewHolder : BaseViewHolder<UiModel>> :
    ListAdapter<UiModel, ViewHolder>(DefaultDiffUtilCallback()) {

    fun updateListItems(newItems: List<UiModel>) = submitList(newItems)

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}