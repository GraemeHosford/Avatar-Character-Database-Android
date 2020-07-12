package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class SingleResponseProcessorTest {

    private lateinit var processor: TestSingleResponseProcessor

    @Before
    fun setup() {
        processor = TestSingleResponseProcessor()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun process_correctlyConvertsResponse_toEntity() = runBlocking {
        val response = TestResponse()

        val expectedEntity = TestEntity(1L, "Aang")

        val entity = processor.process(response)

        assertThat(entity, equalTo(expectedEntity))
    }

    private data class TestResponse(val id: Long = 1L, val name: String = "Aang")
    private data class TestEntity(val id: Long, val name: String)

    private inner class TestResponseConverter : ResponseConverter<TestResponse, TestEntity> {
        override fun toEntity(response: TestResponse) = with(response) {
            TestEntity(
                id,
                name
            )
        }
    }

    private inner class TestSingleResponseProcessor :
        SingleResponseProcessor<TestResponse, TestEntity>(TestResponseConverter())
}