package graeme.hosford.avatarcharacterdatabase.entity.layer

import androidx.room.Entity
import androidx.room.PrimaryKey
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val characterId: String,
    val characterName: String,
    val allies: List<String>?,
    val enemies: List<String>?,
    val photoUrl: String?,
    val gender: String?,
    val eyeColour: String?,
    val hairColour: String?,
    val skinColour: String?,
    val weapon: String?,
    val loves: String?,
    val profession: String?,
    val position: String?,
    val predecessor: String?,
    val affiliation: String?,
    val first: String?,
    val voicedBy: String?
) {
    companion object {
        const val TABLE_NAME = "character_table"
    }
}