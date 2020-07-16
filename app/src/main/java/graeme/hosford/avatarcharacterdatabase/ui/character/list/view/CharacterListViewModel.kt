package graeme.hosford.avatarcharacterdatabase.ui.character.list.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.repo.character.CharacterRepo
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterListViewModel @ViewModelInject constructor(
    private val characterRepo: CharacterRepo,
    private val characterListItemUiModelProcessor: CharacterListItemUiModelProcessor
) : BaseViewModel() {

    private val charactersMutable = MutableLiveData<List<CharacterListItemUiModel>>()
    val characters: LiveData<List<CharacterListItemUiModel>>
        get() = charactersMutable

    fun getCharacterList() {
        viewModelScope.launch {
            characterRepo.getCharacterList().collect {
                when (it) {
                    is RepoState.Completed -> {
                        val characterUiModels = characterListItemUiModelProcessor.process(it.data)
                        charactersMutable.value = characterUiModels
                        errorMutable.value = false
                    }
                    is RepoState.Error -> errorMutable.value = true
                }
            }
        }
    }

}