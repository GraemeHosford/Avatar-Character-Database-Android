package graeme.hosford.avatarcharacterdatabase.ui.character.detail.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.character.CharacterRepo
import graeme.hosford.avatarcharacterdatabase.ui.character.detail.model.CharacterDetailUiModel
import graeme.hosford.avatarcharacterdatabase.ui.character.detail.model.CharacterDetailUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDetailViewModel @ViewModelInject constructor(
    private val characterRepo: CharacterRepo,
    private val processor: CharacterDetailUiModelProcessor
) : BaseViewModel<CharacterEntity>() {

    private val characterMutable = MutableLiveData<CharacterDetailUiModel>()
    val characterData: LiveData<CharacterDetailUiModel>
        get() = characterMutable

    fun loadCharacterDetails(id: Long, networkId: String) {
        viewModelScope.launch {
            characterRepo.getSingleCharacter(id, networkId).collect {
                handleRepoStateResult(it)
            }
        }
    }

    override suspend fun doOnCompletedResult(result: CharacterEntity) {
        super.doOnCompletedResult(result)
        val model = processor.process(result)
        characterMutable.value = model
    }
}