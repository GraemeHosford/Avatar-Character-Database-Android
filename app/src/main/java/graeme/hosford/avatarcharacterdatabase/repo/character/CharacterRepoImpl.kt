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

    @ExperimentalCoroutinesApi
    override suspend fun getSingleCharacter(id: String): CharacterEntity {
        val characterResponse = avatarRetrofitService.getCharacterById(id)
        return singleCharacterProcessor.process(characterResponse)
    }
}