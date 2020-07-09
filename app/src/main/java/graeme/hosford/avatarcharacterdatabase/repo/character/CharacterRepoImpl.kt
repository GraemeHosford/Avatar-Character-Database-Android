package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList

class CharacterRepoImpl constructor(
    private val avatarRetrofitService: AvatarCharacterRetrofitService,
    private val characterDao: CharacterDao,
    private val processor: CharacterResponseProcessor
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

            val entities = arrayListOf<CharacterEntity>()
            processor.process(responses).toList(entities)
            entities
        }
    }
}