package graeme.hosford.avatarcharacterdatabase.network.character

import graeme.hosford.avatarcharacterdatabase.network.common.BaseRetrofitService

class AvatarCharacterRetrofitServiceImpl : BaseRetrofitService<AvatarCharacterRetrofitService>(),
    AvatarCharacterRetrofitService {

    override fun getServiceClass(): Class<AvatarCharacterRetrofitService> =
        AvatarCharacterRetrofitService::class.java

    override fun getAllCharacters(perPage: Int): List<CharacterResponse> =
        service.getAllCharacters(perPage)
}