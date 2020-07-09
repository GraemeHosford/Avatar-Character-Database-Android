package graeme.hosford.avatarcharacterdatabase.ui.character.list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import graeme.hosford.avatarcharacterdatabase.databinding.CharacterListItemLayoutBinding
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder

class CharacterListRecyclerViewAdapter :
    BaseRecyclerViewAdapter<CharacterListItemUiModel, CharacterListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(
            CharacterListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class CharacterListViewHolder(private val binding: CharacterListItemLayoutBinding) :
    BaseViewHolder<CharacterListItemUiModel>(binding.root) {
    override fun bind(model: CharacterListItemUiModel) {
        binding.characterNameTextView.text = model.name
    }
}