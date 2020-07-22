package graeme.hosford.avatarcharacterdatabase.database.layer.character

import androidx.annotation.StringDef
import graeme.hosford.avatarcharacterdatabase.database.layer.character.CharacterOrderBy.Companion.CHARACTER_NAME

@StringDef(CHARACTER_NAME)
@Retention(AnnotationRetention.SOURCE)
annotation class CharacterOrderBy {
    companion object {
        const val CHARACTER_NAME = "characterName"
    }
}