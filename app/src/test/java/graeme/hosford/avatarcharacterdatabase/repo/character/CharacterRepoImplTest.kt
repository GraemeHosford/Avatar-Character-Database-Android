package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
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

        val emits = arrayListOf<RepoState<List<CharacterEntity>>>()
        repo.getCharacterList().toList(emits)

        assertTrue(emits[0] is RepoState.Loading)
        coVerify(exactly = 0) { service.getAllCharacters(AvatarCharacterRetrofitService.CHARACTERS_PER_PAGE) }

        assertThat((emits[1] as RepoState.Completed).data, equalTo(expectedEntities))
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

        val emits = arrayListOf<RepoState<List<CharacterEntity>>>()
        repo.getCharacterList().toList(emits)

        coVerify { dao.getAllCharacters() }
        coVerify { service.getAllCharacters(AvatarCharacterRetrofitService.CHARACTERS_PER_PAGE) }
        coVerify { characterListResponseProcessor.process(responses) }
        coVerify { dao.save(expectedEntities) }
        coEvery { dao.getAllCharacters() } returns expectedEntities

        assertTrue(emits[0] is RepoState.Loading)
        val data = (emits[1] as RepoState.Completed).data
        assertThat(data, equalTo(expectedEntities))
    }

    @Test
    fun getCharacterList_emitsRepoStateError_onError() = runBlocking {
        coEvery { dao.getAllCharacters() } throws Exception()

        val emits = arrayListOf<RepoState<List<CharacterEntity>>>()
        repo.getCharacterList().toList(emits)

        assertTrue(emits[0] is RepoState.Loading)
        assertTrue(emits[1] is RepoState.Error)
    }

    @Test(expected = Exception::class)
    fun getSingleCharacter_callsDatabaseAndNetwork() = runBlocking {
        val expectedDatabaseEntity = getCharacterEntity(5L, name = "Aang")
        coEvery { dao.getCharacterById(5L) } returns expectedDatabaseEntity

        val response = getResponse(
            "TestId",
            characterName = "Aang",
            allies = listOf("Iroh"),
            enemies = listOf("Aang")
        )
        val processedEntity = getCharacterEntity(
            1L,
            characterId = "TestId",
            name = "Aang",
            allies = listOf("Iroh"),
            enemies = listOf("Aang")
        )
        coEvery { service.getCharacterById("TestId") } returns response

        coEvery { singleCharacterResponseProcessor.process(response) } returns processedEntity
        coEvery {
            dao.updateCharacterByNetworkId(
                "TestId", listOf("Iroh"), listOf("Aang"),
                null, null, null, null, null, null,
                null, null, null, null, null, null
            )
        } throws Exception("Mocking a function with no return")

        val emits = arrayListOf<RepoState<CharacterEntity>>()
        repo.getSingleCharacter(5L, "TestId").toList(emits)

        coVerify { dao.getCharacterById(5L) }
        coVerify { service.getCharacterById("TestId") }
        coVerify { singleCharacterResponseProcessor.process(response) }
        coVerify {
            dao.updateCharacterByNetworkId(
                "TestId", listOf("Iroh"), listOf("Aang"), null, null,
                null, null, null, null, null,
                null, null, null, null, null
            )
        }

        assertTrue(emits[0] is RepoState.Loading)
        assertThat((emits[1] as RepoState.Completed).data, equalTo(expectedDatabaseEntity))
        assertThat((emits[2] as RepoState.Completed).data, equalTo(expectedDatabaseEntity))
    }

    @Test
    fun getSingleCharacter_emitsRepoStateError_onError() = runBlocking {
        coEvery { dao.getCharacterById(3L) } throws Exception()

        val emits = arrayListOf<RepoState<CharacterEntity>>()
        repo.getSingleCharacter(3L, "Test").toList(emits)

        assertTrue(emits[0] is RepoState.Loading)
        assertTrue(emits[1] is RepoState.Error)
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