package graeme.hosford.avatarcharacterdatabase.common.view.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import graeme.hosford.avatarcharacterdatabase.common.uimodel.BaseUiModel

abstract class BaseViewHolder<UiModel : BaseUiModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: UiModel)
}