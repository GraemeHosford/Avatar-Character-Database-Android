package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterRepoImplTest {

    private lateinit var repo: CharacterRepoImpl

    @RelaxedMockK
    private lateinit var service: AvatarCharacterRetrofitService

    @RelaxedMockK
    private lateinit var dao: CharacterDao

    @RelaxedMockK
    private lateinit var characterListResponseProcessor: CharacterListResponseProcessor

    @RelaxedMockK
    private lateinit var singleCharacterResponseProcessor: SingleCharacterResponseProcessor

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repo = CharacterRepoImpl(
            service,
            dao,
            characterListResponseProcessor,
            singleCharacterResponseProcessor
        )
    }

    @Test
    fun getCharacterList_returnsDatabaseValues_whenDatabase_isNotEmpty() = runBlocking {
        val expectedEntities = listOf(
            getCharacterEntity(1L, name = "Aang")
        )

        coEvery { dao.getAllCharacters() } returns expectedEntities

        val entities = repo.getCharacterList()

        coVerify(exactly = 0) { service.getAllCharacters(AvatarCharacterRetrofitService.CHARACTERS_PER_PAGE) }

        assertThat(entities, equalTo(expectedEntities))
    }

    @Test(expected = Exception::class)
    fun getCharacterList_callsNetwork_andSavesToDatabase_whenDatabase_isEmpty() = runBlocking {
        coEvery { dao.getAllCharacters() } returns emptyList()

        val responses = listOf(
            getResponse(characterName = "Sokka")
        )

        val expectedEntities = listOf(
            getCharacterEntity(1L, name = "Sokka")
        )

        coEvery { service.getAllCharacters(AvatarCharacterRetrofitService.CHARACTERS_PER_PAGE) } returns responses
        coEvery { characterListResponseProcessor.process(responses) } returns expectedEntities
        coEvery { dao.save(expectedEntities) } throws Exception("Just need something here")

        val entities = repo.getCharacterList()

        coVerify { dao.getAllCharacters() }
        coVerify { service.getAllCharacters(AvatarCharacterRetrofitService.CHARACTERS_PER_PAGE) }
        coVerify { characterListResponseProcessor.process(responses) }
        coVerify { dao.save(expectedEntities) }
        coEvery { dao.getAllCharacters() } returns expectedEntities

        assertThat(entities, equalTo(expectedEntities))
    }

    @Test(expected = Exception::class)
    fun getSingleCharacter_callsDatabaseAndNetwork() = runBlocking {
        val expectedDatabaseEntity = getCharacterEntity(5L, name = "Aang")
        coEvery { dao.getCharacterById(5L) } returns expectedDatabaseEntity

        val response = getResponse("Id", characterName = "Aang")
        val processedEntity = getCharacterEntity(1L, characterId = "id", name = "Aang")
        coEvery { service.getCharacterById("TestId") } returns response

        coEvery { singleCharacterResponseProcessor.process(response) } returns processedEntity
        coEvery {
            dao.updateCharacterByNetworkId(
                "TestId", null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null
            )
        } throws Exception("Mocking a function with no return")

        val entityList = arrayListOf<CharacterEntity>()
        repo.getSingleCharacter(5L, "TestId").take(2).toList(entityList)

        coVerify { dao.getCharacterById(5L) }
        coVerify { service.getCharacterById("TestId") }
        coVerify { singleCharacterResponseProcessor.process(response) }
        coVerify {
            dao.updateCharacterByNetworkId(
                "TestId", null, null, null, null,
                null, null, null, null, null,
                null, null, null, null, null
            )
        }

        assertThat(entityList[0], equalTo(expectedDatabaseEntity))
        assertThat(entityList[1], equalTo(processedEntity))
    }

    private fun getResponse(
        characterId: String = "TestId",
        characterName: String = "Aang"
    ) = CharacterResponse(
        characterId,
        characterName,
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