package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(
    private val avatarRetrofitService: AvatarCharacterRetrofitService,
    private val characterDao: CharacterDao,
    private val characterListProcessor: CharacterListResponseProcessor,
    private val singleCharacterProcessor: SingleCharacterResponseProcessor
) : CharacterRepo {
    @ExperimentalCoroutinesApi
    override suspend fun getCharacterList(): List<CharacterEntity> {
        val characters = characterDao.getAllCharacters()

        return if (characters.isNotEmpty()) {
            characters
        } else {
            val responses = avatarRetrofitService.getAllCharacters(
                AvatarCharacterRetrofitService.CHARACTERS_PER_PAGE
            )

            val entities = characterListProcessor.process(responses)
            characterDao.save(entities)
            entities
        }
    }

    /*
    * The API gives back different responses for characters depending on whether or not a
    * characterId is included in the request. Getting a list of all characters will include less
    * info in the JSON response for each individual character than getting a
    * single character by their Id.
    *
    * This means that despite getting all character info on app startup for the list of characters
    * and storing it in the database, we cannot simply pull from the database here and avoid the
    * network call as some information will be missing in the entity when the detail is looked at
    * for the first time. This means a network call must happen every time and cannot be avoided
    * when the database is already populated like in #getCharacterList.
    *
    * The result of this is that when opening a character detail for the first time the list will
    * briefly contain only partial data until the network response comes through. Each time opening
    * the character detail after that will not have this issue but the network request will
    * still be made regardless.
    * */
    @ExperimentalCoroutinesApi
    override suspend fun getSingleCharacter(id: Long, networkId: String) = flow {
        emit(characterDao.getCharacterById(id))

        val response = avatarRetrofitService.getCharacterById(networkId)
        val entity = singleCharacterProcessor.process(response)
        characterDao.updateCharacterByNetworkId(
            networkId,
            entity.gender,
            entity.eyeColour,
            entity.hairColour,
            entity.skinColour,
            entity.weapon,
            entity.loves,
            entity.profession,
            entity.position,
            entity.predecessor,
            entity.affiliation,
            entity.first,
            entity.voicedBy
        )
        emit(entity)
    }
}