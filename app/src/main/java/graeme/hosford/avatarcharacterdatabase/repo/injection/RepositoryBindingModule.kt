package graeme.hosford.avatarcharacterdatabase.repo.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import graeme.hosford.avatarcharacterdatabase.repo.character.detail.CharacterDetailRepo
import graeme.hosford.avatarcharacterdatabase.repo.character.detail.CharacterDetailRepoImpl
import graeme.hosford.avatarcharacterdatabase.repo.character.list.CharacterListRepo
import graeme.hosford.avatarcharacterdatabase.repo.character.list.CharacterListRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryBindingModule {

    @Binds
    @Singleton
    abstract fun bindCharacterListRepo(impl: CharacterListRepoImpl): CharacterListRepo

    @Binds
    @Singleton
    abstract fun bindCharacterDetailRepo(impl: CharacterDetailRepoImpl): CharacterDetailRepo

}