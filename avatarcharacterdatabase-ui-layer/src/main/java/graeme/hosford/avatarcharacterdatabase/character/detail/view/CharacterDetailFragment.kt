package graeme.hosford.avatarcharacterdatabase.character.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import graeme.hosford.avatarcharacterdatabase.character.detail.model.CharacterSingleDetailUiModel
import graeme.hosford.avatarcharacterdatabase.character.detail.view.adapter.CharacterDetailRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.character.detail.view.adapter.CharacterDetailViewHolder
import graeme.hosford.avatarcharacterdatabase.common.view.fragment.BaseRecyclerViewFragment
import graeme.hosford.avatarcharacterdatabase.databinding.FragmentCharacterDetailLayoutBinding

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseRecyclerViewFragment<
            CharacterSingleDetailUiModel,
            CharacterDetailViewHolder,
            CharacterDetailRecyclerViewAdapter,
            CharacterDetailViewModel>() {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentCharacterDetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.loadCharacterDetails(args.characterId, args.characterNetworkId)
    }

    override fun registerViewModel() = characterDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.characterData.observe(viewLifecycleOwner, Observer {
            if (it.photoUrl.isNullOrBlank()) {
                binding.characterDetailPicture.visibility = View.GONE
            } else {
                Glide.with(this)
                    .load(it.photoUrl)
                    .circleCrop()
                    .into(binding.characterDetailPicture)
            }

            recyclerViewAdapter.updateListItems(it.details)
        })
    }

    override fun recyclerViewAdapter() = CharacterDetailRecyclerViewAdapter()

    override fun getBaseRecyclerViewLayout() = binding.characterDetailBaseRecyclerViewLayout
}