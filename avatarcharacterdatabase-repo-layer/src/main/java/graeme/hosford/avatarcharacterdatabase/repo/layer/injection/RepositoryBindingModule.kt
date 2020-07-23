package graeme.hosford.avatarcharacterdatabase.repo.layer.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.detail.CharacterDetailRepo
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.detail.CharacterDetailRepoImpl
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.list.CharacterListRepo
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.list.CharacterListRepoImpl
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