package graeme.hosford.avatarcharacterdatabase.character.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import graeme.hosford.avatarcharacterdatabase.character.detail.model.CharacterSingleDetailUiModel
import graeme.hosford.avatarcharacterdatabase.common.view.recyclerview.BaseRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.common.view.recyclerview.BaseViewHolder
import graeme.hosford.avatarcharacterdatabase.databinding.CharacterSingleDetailLayoutItemBinding

class CharacterDetailRecyclerViewAdapter :
    BaseRecyclerViewAdapter<CharacterSingleDetailUiModel, CharacterDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterDetailViewHolder(
        CharacterSingleDetailLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}

class CharacterDetailViewHolder(
    private val binding: CharacterSingleDetailLayoutItemBinding
) : BaseViewHolder<CharacterSingleDetailUiModel>(binding.root) {
    override fun bind(model: CharacterSingleDetailUiModel) {
        binding.characterDetailTitle.text = binding.root.context.getString(model.titleString)
        binding.characterDetailValue.text = model.detail
    }
}