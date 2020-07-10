package graeme.hosford.avatarcharacterdatabase.ui.character.detail.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import graeme.hosford.avatarcharacterdatabase.ui.common.view.fragment.BaseFragment

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private val viewmodel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

}