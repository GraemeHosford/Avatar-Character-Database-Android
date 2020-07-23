package graeme.hosford.avatarcharacterdatabase.common.view.recyclerview

import androidx.recyclerview.widget.DiffUtil
import graeme.hosford.avatarcharacterdatabase.common.uimodel.BaseUiModel

class DefaultDiffUtilCallback<UiModel : BaseUiModel> : DiffUtil.ItemCallback<UiModel>() {
    override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        return oldItem == newItem
    }
}