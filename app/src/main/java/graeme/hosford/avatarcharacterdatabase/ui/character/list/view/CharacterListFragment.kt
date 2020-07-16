package graeme.hosford.avatarcharacterdatabase.ui.character.list.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.list.view.adapter.CharacterListRecyclerViewAdapter
import graeme.hosford.avatarcharacterdatabase.ui.character.list.view.adapter.CharacterListViewHolder
import graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment.BaseRecyclerViewFragment

@AndroidEntryPoint
class CharacterListFragment :
    BaseRecyclerViewFragment<
            CharacterListItemUiModel,
            CharacterListViewHolder,
            CharacterListRecyclerViewAdapter,
            CharacterListViewModel>() {

    private val viewmodel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getCharacterList()
    }

    override fun registerViewModel() = viewmodel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.characters.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.updateListItems(it)
        })
    }

    override fun recyclerViewAdapter(): CharacterListRecyclerViewAdapter =
        CharacterListRecyclerViewAdapter { id, networkId, characterName ->
            findNavController().navigate(
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    id,
                    characterName,
                    networkId
                )
            )
        }.apply {
            setHasStableIds(true)
        }
}