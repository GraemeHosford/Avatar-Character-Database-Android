package graeme.hosford.avatarcharacterdatabase.ui.character.list.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.character.list.CharacterListRepo
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterListViewModel @ViewModelInject constructor(
    private val repo: CharacterListRepo,
    private val characterListItemUiModelProcessor: CharacterListItemUiModelProcessor
) : BaseViewModel<List<CharacterEntity>>() {

    private val charactersMutable = MutableLiveData<List<CharacterListItemUiModel>>()
    val characters: LiveData<List<CharacterListItemUiModel>>
        get() = charactersMutable

    fun getCharacterList() {
        viewModelScope.launch {
            repo.getCharacterList().collect {
                handleRepoStateResult(it)
            }
        }
    }

    override suspend fun doOnCompletedResult(result: List<CharacterEntity>) {
        super.doOnCompletedResult(result)
        val models = characterListItemUiModelProcessor.process(result)
        charactersMutable.value = models
    }
}