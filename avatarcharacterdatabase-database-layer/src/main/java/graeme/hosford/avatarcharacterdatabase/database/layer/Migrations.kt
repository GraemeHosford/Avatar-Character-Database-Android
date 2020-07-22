package graeme.hosford.avatarcharacterdatabase.database.layer

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE character_table ADD COLUMN allies TEXT")
            database.execSQL("ALTER TABLE character_table ADD COLUMN enemies TEXT")
        }
    }
}