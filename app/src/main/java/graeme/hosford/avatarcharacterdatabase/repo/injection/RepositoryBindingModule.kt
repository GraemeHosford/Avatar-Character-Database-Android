package graeme.hosford.avatarcharacterdatabase.repo.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import graeme.hosford.avatarcharacterdatabase.repo.character.CharacterRepo
import graeme.hosford.avatarcharacterdatabase.repo.character.CharacterRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryBindingModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(impl: CharacterRepoImpl): CharacterRepo

}