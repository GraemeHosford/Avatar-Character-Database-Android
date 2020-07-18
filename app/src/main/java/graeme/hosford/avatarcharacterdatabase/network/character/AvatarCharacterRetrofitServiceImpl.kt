package graeme.hosford.avatarcharacterdatabase.network.character

import graeme.hosford.avatarcharacterdatabase.network.common.BaseRetrofitService
import javax.inject.Inject

class AvatarCharacterRetrofitServiceImpl @Inject constructor() :
    BaseRetrofitService<AvatarCharacterRetrofitService>(),
    AvatarCharacterRetrofitService {

    override fun getServiceClass(): Class<AvatarCharacterRetrofitService> =
        AvatarCharacterRetrofitService::class.java

    override suspend fun getAllCharacters(perPage: Int, pageNum: Int) =
        service.getAllCharacters(perPage, pageNum)

    override suspend fun getCharacterById(characterId: String) =
        service.getCharacterById(characterId)
}