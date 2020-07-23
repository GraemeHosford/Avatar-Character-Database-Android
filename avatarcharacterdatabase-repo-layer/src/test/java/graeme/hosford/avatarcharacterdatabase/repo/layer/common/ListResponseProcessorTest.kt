package graeme.hosford.avatarcharacterdatabase.repo.layer.common

import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class ListResponseProcessorTest {

    private lateinit var processor: TestListResponseProcessor

    @Before
    fun setup() {
        processor = TestListResponseProcessor()
    }

    @Test
    fun process_correctlyConvertsResponses_toEntities() = runBlocking {
        val responses = listOf(
            TestResponse(
                1L,
                "Aang"
            ),
            TestResponse(
                2L,
                "Katara"
            )
        )

        val expectedEntities = listOf(
            TestEntity(
                1L,
                "Aang"
            ),
            TestEntity(
                2L,
                "Katara"
            )
        )

        val entities = processor.process(responses)

        assertThat(entities, equalTo(expectedEntities))
    }

    private data class TestResponse(val id: Long = 1L, val name: String = "Aang")
    private data class TestEntity(val id: Long, val name: String)

    private inner class TestResponseConverter :
        ResponseConverter<TestResponse, TestEntity> {
        override fun toEntity(response: TestResponse) = with(response) {
            TestEntity(
                id,
                name
            )
        }
    }

    private inner class TestListResponseProcessor :
        ListResponseProcessor<TestResponse, TestEntity>(TestResponseConverter())
}