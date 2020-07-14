package graeme.hosford.avatarcharacterdatabase.ui.character.list.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.repo.character.CharacterRepo
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModelProcessor
import kotlinx.coroutines.launch

class CharacterListViewModel @ViewModelInject constructor(
    private val characterRepo: CharacterRepo,
    private val characterListItemUiModelProcessor: CharacterListItemUiModelProcessor
) : ViewModel() {

    private val charactersMutable = MutableLiveData<List<CharacterListItemUiModel>>()
    val characters: LiveData<List<CharacterListItemUiModel>>
        get() = charactersMutable

    fun getCharacterList() {
        viewModelScope.launch {
            val characterEntities = characterRepo.getCharacterList()
            val characterUiModels = characterListItemUiModelProcessor.process(characterEntities)
            charactersMutable.value = characterUiModels
        }
    }

}