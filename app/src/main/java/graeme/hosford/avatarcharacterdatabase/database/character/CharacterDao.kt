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

}