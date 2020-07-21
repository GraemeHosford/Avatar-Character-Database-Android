package graeme.hosford.avatarcharacterdatabase.ui.character.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import graeme.hosford.avatarcharacterdatabase.databinding.CharacterListItemLayoutBinding
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BasePaginatedRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.common.view.recyclerview.BaseViewHolder

typealias CharacterItemOnClick = (Long, String, String) -> Unit

class CharacterListRecyclerViewAdapter(private val onItemClick: CharacterItemOnClick) :
    BasePaginatedRecyclerViewAdapter<CharacterListItemUiModel, CharacterListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(
            CharacterListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }
}

class CharacterListViewHolder(
    private val binding: CharacterListItemLayoutBinding,
    private val characterItemOnClick: CharacterItemOnClick
) : BaseViewHolder<CharacterListItemUiModel>(binding.root) {
    override fun bind(model: CharacterListItemUiModel) {
        binding.root.setOnClickListener {
            characterItemOnClick(model.id, model.networkId, model.name)
        }

        binding.characterNameTextView.text = model.name

        if (model.photoUrl == null) {
            binding.characterPictureImageView.visibility = View.GONE
        } else {
            binding.characterPictureImageView.visibility = View.VISIBLE
            Glide.with(binding.root)
                .load(model.photoUrl)
                .centerCrop()
                .into(binding.characterPictureImageView)
        }
    }
}