package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.SingleUiModelProcessor
import javax.inject.Inject

class CharacterDetailUiModelProcessor @Inject constructor(
    converter: CharacterDetailUiModelConverter
) : SingleUiModelProcessor<CharacterEntity, CharacterDetailUiModel>(converter)