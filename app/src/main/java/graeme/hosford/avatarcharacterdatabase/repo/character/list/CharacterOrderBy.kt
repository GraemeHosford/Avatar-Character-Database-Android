package graeme.hosford.avatarcharacterdatabase.repo.character.list

import androidx.annotation.StringDef
import graeme.hosford.avatarcharacterdatabase.repo.character.list.CharacterOrderBy.Companion.CHARACTER_NAME

@StringDef(CHARACTER_NAME)
@Retention(AnnotationRetention.SOURCE)
annotation class CharacterOrderBy {
    companion object {
        const val CHARACTER_NAME = "characterName"
    }
}