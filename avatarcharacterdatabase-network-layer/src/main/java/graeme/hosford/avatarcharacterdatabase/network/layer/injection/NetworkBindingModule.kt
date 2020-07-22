package graeme.hosford.avatarcharacterdatabase.network.layer.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import graeme.hosford.avatarcharacterdatabase.network.layer.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.network.layer.character.AvatarCharacterRetrofitServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class NetworkBindingModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRetrofitService(impl: AvatarCharacterRetrofitServiceImpl): AvatarCharacterRetrofitService

}