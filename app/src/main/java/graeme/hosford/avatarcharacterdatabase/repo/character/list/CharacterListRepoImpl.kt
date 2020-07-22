package graeme.hosford.avatarcharacterdatabase.repo.character.list

import androidx.annotation.VisibleForTesting
import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
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

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun getPageSize() = CHARACTERS_PER_PAGE

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override suspend fun fetchFromLocal(
        dao: CharacterDao,
        offset: Int,
        limit: Int
    ) = dao.getAllCharacters(offset, limit, CharacterOrderBy.CHARACTER_NAME)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override suspend fun saveToLocal(dao: CharacterDao, entities: List<CharacterEntity>) =
        dao.save(entities)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override suspend fun fetchFromNetwork(
        service: AvatarCharacterRetrofitService,
        page: Int,
        pageSize: Int
    ): List<CharacterEntity> {
        val responses = service.getAllCharacters(pageSize, page)
        return characterListProcessor.process(responses)
    }
}