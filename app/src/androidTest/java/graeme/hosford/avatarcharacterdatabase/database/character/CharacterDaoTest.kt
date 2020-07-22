package graeme.hosford.avatarcharacterdatabase.database.character

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import graeme.hosford.avatarcharacterdatabase.database.AvatarCharacterDatabase
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.character.list.CharacterOrderBy
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterDaoTest {

    private lateinit var database: AvatarCharacterDatabase
    private lateinit var dao: CharacterDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AvatarCharacterDatabase::class.java
        ).build()

        dao = database.getCharacterDao()
    }

    @After
    fun shutdown() {
        database.close()
    }

    @Test
    fun getAllCharacters_returnsPagesOfTableContents() = runBlocking {
        val entities = listOf(
            getCharacterEntity(1L, name = "Aang", gender = "Male", position = "Avatar"),
            getCharacterEntity(2L, name = "Iroh", gender = "Male", voicedBy = "Mako")
        )

        dao.save(entities)

        val savedEntities = dao.getAllCharacters(0, 5, CharacterOrderBy.CHARACTER_NAME)

        assertThat(entities, equalTo(savedEntities))
    }

    @Test
    fun getAllCharacters_returnsSubsetOfContents_whenLimitAndOffsetApplied() = runBlocking {
        val entities = listOf(
            getCharacterEntity(1L, name = "Aang", gender = "Male", position = "Avatar"),
            getCharacterEntity(2L, name = "Iroh", gender = "Male", voicedBy = "Mako")
        )

        dao.save(entities)

        val expectedResult =
            listOf(getCharacterEntity(2L, name = "Iroh", gender = "Male", voicedBy = "Mako"))

        val savedEntities = dao.getAllCharacters(1, 5, CharacterOrderBy.CHARACTER_NAME)

        assertThat(expectedResult, equalTo(savedEntities))
    }

    @Test
    fun getCharacterById_returns_CorrectCharacterEntity() = runBlocking {
        val entity1 =
            getCharacterEntity(1L, name = "Aang", eyeColour = "Gray", weapon = "Avatar Powers")
        dao.save(entity1)

        val entity2 = getCharacterEntity(2L, name = "Katara", eyeColour = "Blue")
        dao.save(entity2)

        val savedEntity = dao.getCharacterById(2L)

        assertEquals(entity2, savedEntity)
    }

    @Test
    fun updateCharacterByNetworkId_correctlyUpdatesCharacterEntity() = runBlocking {
        val entity1 = getCharacterEntity(1L, characterId = "TestId1", name = "Aang")
        val entity2 = getCharacterEntity(2L, characterId = "TestId2", name = "Zuko")

        dao.save(entity1)
        dao.save(entity2)

        dao.updateCharacterByNetworkId(
            "TestId2", listOf("Iroh"), listOf("Zaheer"), "Male", "Gold",
            "Black", null, null, null, null,
            null, null, null, null, null
        )

        val expectedEntity = getCharacterEntity(
            2L,
            characterId = "TestId2",
            name = "Zuko",
            allies = listOf("Iroh"),
            enemies = listOf("Zaheer"),
            gender = "Male",
            eyeColour = "Gold",
            hairColour = "Black"
        )

        val entity1FromDatabase = dao.getCharacterById(1L)
        val entity2FromDatabase = dao.getCharacterById(2L)

        /* Check that no other entity besides the one specified has been affected */
        assertThat(entity1, equalTo(entity1FromDatabase))
        assertThat(expectedEntity, equalTo(entity2FromDatabase))
    }

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