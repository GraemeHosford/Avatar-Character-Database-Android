package graeme.hosford.avatarcharacterdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val characterId: String,
    val characterName: String,
    // Ignore these for now. Annoying foreign key stuff can be dealt with later
    /*val allies: List<String>,
    val enemies: List<String>,*/
    val photoUrl: String,
    val gender: String,
    val eyeColour: String,
    val hairColour: String,
    val skinColour: String,
    val loves: String,
    val profession: String,
    val predecessor: String,
    val affiliation: String
) {
    companion object {
        const val TABLE_NAME = "character_table"
    }
}