package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterListRepoImplTest {

    private lateinit var repo: CharacterListRepoImpl

    @RelaxedMockK
    private lateinit var service: AvatarCharacterRetrofitService

    @RelaxedMockK
    private lateinit var dao: CharacterDao

    @RelaxedMockK
    private lateinit var characterListResponseProcessor: CharacterListResponseProcessor

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repo = CharacterListRepoImpl(
            service,
            dao,
            characterListResponseProcessor
        )
    }

    @Test
    fun getPageSize_returnsCharactersPerPage_fromRepo() {
        assertEquals(CHARACTERS_PER_PAGE, repo.getPageSize())
    }

    @Test
    fun fetchFromLocal_calls_getAllCharacters() = runBlocking {
        val entities = listOf(getCharacterEntity(1L, name = "Aang"))

        coEvery {
            dao.getAllCharacters(
                0,
                CHARACTERS_PER_PAGE,
                CharacterOrderBy.CHARACTER_NAME
            )
        } returns entities

        repo.fetchFromLocal(dao, 0, CHARACTERS_PER_PAGE)

        coVerify { dao.getAllCharacters(0, CHARACTERS_PER_PAGE, CharacterOrderBy.CHARACTER_NAME) }
    }

    @Test(expected = Exception::class)
    fun saveToLocal_calls_daoSave() = runBlocking {
        val entities = listOf(getCharacterEntity(1L, name = "Aang"))

        coEvery {
            dao.save(entities)
        } throws Exception("Mocking that dao save was called")

        repo.saveToLocal(dao, entities)
    }

    @Test
    fun fetchFromNetwork_callsService_andProcessor() = runBlocking {
        val responses =
            listOf(getResponse(characterName = "Aang"), getResponse(characterName = "Iroh"))

        val entities = listOf(
            getCharacterEntity(1L, characterId = "TestId", name = "Aang"),
            getCharacterEntity(2L, characterId = "TestId", name = "Iroh")
        )

        coEvery { service.getAllCharacters(5, 1) } returns responses
        coEvery { characterListResponseProcessor.process(responses) } returns entities

        val result = repo.fetchFromNetwork(service, 1, 5)

        assertThat(entities, equalTo(result))
    }

    private fun getResponse(
        characterId: String = "TestId",
        characterName: String = "Aang",
        allies: List<String> = emptyList(),
        enemies: List<String> = emptyList()
    ) = CharacterResponse(
        characterId,
        characterName,
        allies,
        enemies,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    private fun getCharacterEntity(
        id: Long,
        characterId: String = "FakeId",
        name: String,
        allies: List<String>? = null,
        enemies: List<String>? = null,
        photoUrl: String? = null,
        gender: String? = null,
        eyeColour: String? = null,
        hairColour: String? = null,
        skinColour: String? = null,
        weapon: String? = null,
        loves: String? = null,
        profession: String? = null,
        position: String? = null,
        predecessor: String? = null,
        affiliation: String? = null,
        first: String? = null,
        voicedBy: String? = null
    ) = CharacterEntity(
        id,
        characterId,
        name,
        allies,
        enemies,
        photoUrl,
        gender,
        eyeColour,
        hairColour,
        skinColour,
        weapon,
        loves,
        profession,
        position,
        predecessor,
        affiliation,
        first,
        voicedBy
    )
}