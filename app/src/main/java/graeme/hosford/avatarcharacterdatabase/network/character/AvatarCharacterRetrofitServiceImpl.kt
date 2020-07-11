package graeme.hosford.avatarcharacterdatabase.network.character

import graeme.hosford.avatarcharacterdatabase.network.common.BaseRetrofitService
import javax.inject.Inject

class AvatarCharacterRetrofitServiceImpl @Inject constructor() :
    BaseRetrofitService<AvatarCharacterRetrofitService>(),
    AvatarCharacterRetrofitService {

    override fun getServiceClass(): Class<AvatarCharacterRetrofitService> =
        AvatarCharacterRetrofitService::class.java

    override suspend fun getAllCharacters(perPage: Int) = service.getAllCharacters(perPage)

    override suspend fun getCharacterById(characterId: String) =
        service.getCharacterById(characterId)
}