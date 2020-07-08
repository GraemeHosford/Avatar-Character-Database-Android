package graeme.hosford.avatarcharacterdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import graeme.hosford.avatarcharacterdatabase.database.AvatarCharacterDatabase.Companion.DATABASE_VERSION
import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity

@Database(version = DATABASE_VERSION, entities = [CharacterEntity::class])
abstract class AvatarCharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

    companion object {
        private const val DATABASE_NAME = "avatar_character_database"
        const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: AvatarCharacterDatabase? = null

        fun getInstance(context: Context): AvatarCharacterDatabase {
            val instanceHolder = INSTANCE

            if (instanceHolder != null) {
                return instanceHolder
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AvatarCharacterDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}