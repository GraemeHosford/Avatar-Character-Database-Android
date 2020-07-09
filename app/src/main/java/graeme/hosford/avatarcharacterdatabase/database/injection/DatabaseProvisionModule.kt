package graeme.hosford.avatarcharacterdatabase.database.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import graeme.hosford.avatarcharacterdatabase.database.AvatarCharacterDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseProvisionModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = AvatarCharacterDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideCharacterDao(database: AvatarCharacterDatabase) = database.getCharacterDao()
}