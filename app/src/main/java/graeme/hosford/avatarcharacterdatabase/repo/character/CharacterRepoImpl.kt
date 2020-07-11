package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    * Not using the database as a cache in this method due to inconsistent REST API responses.
    *
    * The API gives back different responses for characters depending on whether or not a
    * characterId is included in the request. Getting a list of all characters will include less
    * info in the JSON response for each individual character than getting a
    * single character by their Id.
    *
    * This makes using the database as a cache awkward. To make it work I would need to go through
    * all fields in the CharacterEntity and check each for being null. Certain common fields such
    * as eye colour being null could indicate the character info must be pulled from the network
    * and being not null would indicate the cached value from the database could be used. However
    * the nullability of any particular field is not consistent across all characters so this
    * solution wouldn't work 100% of the time.
    *
    * Beyond that it is a solution which would require a lot of poor hacky code. The purpose of this
    * project is to showcase my own approach to Android app architecture which favours many classes
    * and as clean a codebase as possible. Attempting to cache these values and come up with some
    * check for if a CharacterEntity can be used or not would definitely break this approach to
    * coding.
    *
    * In the end, making the network request every time a character is viewed in detail is the only
    * solution which guarantees the user sees the correct data every time. The downside being
    * increased network traffic where this need not necessarily be the case if the API
    * provided consistent responses for similar requests.
    * */
    @ExperimentalCoroutinesApi
    override suspend fun getSingleCharacter(id: String): CharacterEntity {
        val characterResponse = avatarRetrofitService.getCharacterById(id)
        return singleCharacterProcessor.process(characterResponse)
    }
}