package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class ListUiModelProcessorTest {

    private lateinit var processor: TestListUiModelProcessor

    @Before
    fun setup() {
        processor = TestListUiModelProcessor()
    }

    @Test
    fun processorProcess_returnsListOfUiModel() = runBlocking {
        val entities = listOf(
            TestEntity(1L, "TestName1"),
            TestEntity(2L, "TestName2")
        )

        val expectedModels = listOf(
            TestUiModel(1L, "TestName1"),
            TestUiModel(2L, "TestName2")
        )

        val models = processor.process(entities)

        assertThat(models, equalTo(expectedModels))
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

    private inner class TestListUiModelProcessor : ListUiModelProcessor<TestEntity, TestUiModel>(
        TestConverter(),
        compareBy(TestUiModel::name)
    )
}