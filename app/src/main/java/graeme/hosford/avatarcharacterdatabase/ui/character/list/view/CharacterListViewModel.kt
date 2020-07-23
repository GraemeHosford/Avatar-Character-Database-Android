package graeme.hosford.avatarcharacterdatabase.ui.character.list.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.list.CharacterListRepo
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.list.model.CharacterListItemUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.pagination.BasePaginatedViewModel

class CharacterListViewModel @ViewModelInject constructor(
    repo: CharacterListRepo,
    private val characterListItemUiModelProcessor: CharacterListItemUiModelProcessor
) : BasePaginatedViewModel<CharacterEntity, CharacterListRepo>(repo) {

    private val charactersMutable = MutableLiveData<List<CharacterListItemUiModel>>()
    val characters: LiveData<List<CharacterListItemUiModel>>
        get() = charactersMutable

    fun getCharacterList() {
        requestNextPage()
    }

    override suspend fun doOnPaginatedResult(result: List<CharacterEntity>) {
        super.doOnPaginatedResult(result)
        val models = characterListItemUiModelProcessor.process(result)
        charactersMutable.value = models
    }
}