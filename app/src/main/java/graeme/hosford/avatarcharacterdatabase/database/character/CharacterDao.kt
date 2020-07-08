package graeme.hosford.avatarcharacterdatabase.database.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(character: CharacterEntity)

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}")
    fun getAllCharacters(): List<CharacterEntity>

}