package graeme.hosford.avatarcharacterdatabase.ui.character.list.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.character.list.CharacterListRepo
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BasePaginatedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterListViewModel @ViewModelInject constructor(
    private val repo: CharacterListRepo,
    private val characterListItemUiModelProcessor: CharacterListItemUiModelProcessor
) : BasePaginatedViewModel<CharacterEntity, CharacterListRepo>(repo) {

    private val charactersMutable = MutableLiveData<List<CharacterListItemUiModel>>()
    val characters: LiveData<List<CharacterListItemUiModel>>
        get() = charactersMutable

    fun getCharacterList() {
        viewModelScope.launch {
            repo.getNextPage().collect {
                handlePaginatedRepoStateResult(it)
            }
        }
    }

    override suspend fun doOnPaginatedResult(result: List<CharacterEntity>) {
        super.doOnPaginatedResult(result)
        val models = characterListItemUiModelProcessor.process(result)
        charactersMutable.value = models
    }
}