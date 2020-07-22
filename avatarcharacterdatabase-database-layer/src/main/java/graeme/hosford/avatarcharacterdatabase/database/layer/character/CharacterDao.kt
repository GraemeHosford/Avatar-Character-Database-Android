package graeme.hosford.avatarcharacterdatabase.database.layer.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import graeme.hosford.avatarcharacterdatabase.database.layer.Converters
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(characters: List<CharacterEntity>)

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} ORDER BY :order LIMIT :limit OFFSET :offset")
    suspend fun getAllCharacters(
        offset: Int,
        limit: Int,
        @CharacterOrderBy order: String
    ): List<CharacterEntity>

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getCharacterById(id: Long): CharacterEntity

    @Query(
        """UPDATE ${CharacterEntity.TABLE_NAME} SET allies = :allies, 
        enemies = :enemies, 
        gender = :gender, 
        eyeColour = :eyeColour, 
        hairColour = :hairColour, 
        skinColour = :skinColour, 
        weapon = :weapon, 
        loves = :loves, 
        profession = :profession, 
        position = :position, 
        predecessor = :predecessor, 
        affiliation = :affiliation, 
        first = :first, 
        voicedBy = :voicedBy 
        WHERE characterId = :networkId"""
    )
    suspend fun updateCharacterByNetworkId(
        networkId: String,
        allies: String?,
        enemies: String?,
        gender: String?,
        eyeColour: String?,
        hairColour: String?,
        skinColour: String?,
        weapon: String?,
        loves: String?,
        profession: String?,
        position: String?,
        predecessor: String?,
        affiliation: String?,
        first: String?,
        voicedBy: String?
    )

    /* Function which accepts list of allies and enemies, converts them to json and then calls
    * updateCharacterByNetworkId with string values.
    *
    * This function is needed due to issue with Room causing generated SQL ignoring the type
    * converter and requiring number of arguments equal to size of list rather than single argument
    * for string */
    suspend fun updateCharacterByNetworkId(
        networkId: String,
        allies: List<String>?,
        enemies: List<String>?,
        gender: String?,
        eyeColour: String?,
        hairColour: String?,
        skinColour: String?,
        weapon: String?,
        loves: String?,
        profession: String?,
        position: String?,
        predecessor: String?,
        affiliation: String?,
        first: String?,
        voicedBy: String?
    ) {
        val alliesString = Converters.stringListToJson(allies)
        val enemiesString = Converters.stringListToJson(enemies)

        updateCharacterByNetworkId(
            networkId,
            alliesString,
            enemiesString,
            gender,
            eyeColour,
            hairColour,
            skinColour,
            weapon,
            loves,
            profession,
            position,
            predecessor,
            affiliation,
            first,
            voicedBy
        )
    }

}