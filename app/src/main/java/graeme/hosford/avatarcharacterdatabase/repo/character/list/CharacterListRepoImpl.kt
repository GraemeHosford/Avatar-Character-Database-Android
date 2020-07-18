package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.repo.common.BaseRepo
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterListRepoImpl @Inject constructor(
    private val avatarRetrofitService: AvatarCharacterRetrofitService,
    private val characterDao: CharacterDao,
    private val characterListProcessor: CharacterListResponseProcessor
) : BaseRepo(), CharacterListRepo {

    /*
    * The logic here avoids the network call when the database has values because the data on the
    * server-side is not changing, and will not due to the show being long finished.
    *
    * This allows us to avoid the network call in some instances once the database is populated.
    * This logic would not work if the server-side data was changing and would need to instead match
    * the Flow logic in #getSingleCharacter below.
    *
    * Despite this the logic here may need to change in future anyway if I implement pagination.
    * Right now the network call pulls the first 100 characters from the server when there are
    * close to 500 there. This is a hardcoded limit right now to save on load times and data usage.
    * */
    override suspend fun getCharacterList() = flow<RepoState<List<CharacterEntity>>> {
        emit(RepoState.loading())

        try {
            val characters = characterDao.getAllCharacters()

            if (characters.isNotEmpty()) {
                emit(RepoState.completed(characters))
            } else {
                val responses = avatarRetrofitService.getAllCharacters(CHARACTERS_PER_PAGE)

                val entities = characterListProcessor.process(responses)
                characterDao.save(entities)

                /* Can't just returns the result of processing as database Ids are needed */
                emit(RepoState.completed(characterDao.getAllCharacters()))
            }
        } catch (e: Exception) {
            emit(RepoState.error())
        }
    }
}