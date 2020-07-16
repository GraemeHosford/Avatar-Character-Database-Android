package graeme.hosford.avatarcharacterdatabase.ui.character.detail.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.repo.character.CharacterRepo
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import graeme.hosford.avatarcharacterdatabase.ui.character.detail.model.CharacterDetailUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.detail.model.CharacterDetailUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDetailViewModel @ViewModelInject constructor(
    private val characterRepo: CharacterRepo,
    private val processor: CharacterDetailUiModelProcessor
) : BaseViewModel() {

    private val characterMutable = MutableLiveData<CharacterDetailUiModel>()
    val characterData: LiveData<CharacterDetailUiModel>
        get() = characterMutable

    fun loadCharacterDetails(id: Long, networkId: String) {
        viewModelScope.launch {
            characterRepo.getSingleCharacter(id, networkId).collect {
                when (it) {
                    is RepoState.Completed -> {
                        val characterModel = processor.process(it.data)
                        characterMutable.value = characterModel
                        errorMutable.value = false
                    }
                    is RepoState.Error -> errorMutable.value = true
                }
            }
        }
    }

}