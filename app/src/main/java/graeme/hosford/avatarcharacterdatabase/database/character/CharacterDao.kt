package graeme.hosford.avatarcharacterdatabase.database.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(characters: List<CharacterEntity>)

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getCharacterById(id: Long): CharacterEntity

    @Query(
        """UPDATE ${CharacterEntity.TABLE_NAME} SET gender = :gender, 
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

}