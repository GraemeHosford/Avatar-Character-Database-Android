package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class SingleUiModelProcessorTest {

    private lateinit var processor: TestSingleUiModelProcessor

    @Before
    fun setup() {
        processor = TestSingleUiModelProcessor()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun processorProcess_returnsListOfUiModel() = runBlocking {
        val entity = TestEntity(1L, "TestName")

        val expectedModel = TestUiModel(1L, "TestName")

        val model = processor.process(entity)

        assertThat(model, equalTo(expectedModel))
    }

    private data class TestEntity(
        val id: Long = 1L,
        val name: String
    )

    private inner class TestUiModel(id: Long, val name: String) : BaseUiModel(id) {
        override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
            if (other !is TestUiModel) {
                return false
            }

            return id == other.id
                    && name == other.name
        }
    }

    private inner class TestConverter : UiModelConverter<TestEntity, TestUiModel> {
        override fun toUiModel(entity: TestEntity) = with(entity) {
            TestUiModel(id, name)
        }
    }

    private inner class TestSingleUiModelProcessor :
        SingleUiModelProcessor<TestEntity, TestUiModel>(
            TestConverter()
        )

}