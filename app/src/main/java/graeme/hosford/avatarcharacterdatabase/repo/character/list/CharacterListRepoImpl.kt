package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.repo.common.pagination.BasePaginatedRepo
import javax.inject.Inject

class CharacterListRepoImpl @Inject constructor(
    service: AvatarCharacterRetrofitService,
    dao: CharacterDao,
    private val characterListProcessor: CharacterListResponseProcessor
) : BasePaginatedRepo<AvatarCharacterRetrofitService, CharacterDao, CharacterEntity>(
    service,
    dao
), CharacterListRepo {

    override fun getPageSize() = CHARACTERS_PER_PAGE

    override suspend fun fetchFromLocal(
        dao: CharacterDao,
        offset: Int,
        limit: Int
    ) = dao.getAllCharacters(offset, limit, CharacterOrderBy.CHARACTER_NAME)

    override suspend fun saveToLocal(dao: CharacterDao, entities: List<CharacterEntity>) =
        dao.save(entities)

    override suspend fun fetchFromNetwork(
        service: AvatarCharacterRetrofitService,
        page: Int,
        pageSize: Int
    ): List<CharacterEntity> {
        val responses = service.getAllCharacters(pageSize, page)
        return characterListProcessor.process(responses)
    }
}