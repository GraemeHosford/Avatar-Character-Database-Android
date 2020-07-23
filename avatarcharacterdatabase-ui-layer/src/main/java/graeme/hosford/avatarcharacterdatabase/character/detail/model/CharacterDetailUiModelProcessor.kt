package graeme.hosford.avatarcharacterdatabase.character.detail.model

import graeme.hosford.avatarcharacterdatabase.common.uimodel.SingleUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import javax.inject.Inject

class CharacterDetailUiModelProcessor @Inject constructor(
    converter: CharacterDetailUiModelConverter
) : SingleUiModelProcessor<CharacterEntity, CharacterDetailUiModel>(converter)