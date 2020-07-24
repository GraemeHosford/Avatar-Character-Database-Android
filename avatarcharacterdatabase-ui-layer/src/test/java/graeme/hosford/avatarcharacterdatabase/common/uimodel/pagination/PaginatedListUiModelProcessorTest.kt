package graeme.hosford.avatarcharacterdatabase.common.uimodel.pagination

import graeme.hosford.avatarcharacterdatabase.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.common.uimodel.UiModelConverter
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PaginatedListUiModelProcessorTest {

    private lateinit var processor: TestPaginatedListUiModelProcessor

    @Before
    fun setup() {
        processor = TestPaginatedListUiModelProcessor()
    }

    @Test
    fun processAppendsNewModelsToExistingList() = runBlocking {
        val entity1 = listOf(TestEntity(1L, "Test1"))

        val model1 = TestModel(1L, "Test1")
        val model1List = listOf(model1)

        val processorResult = processor.process(entity1)
        assertThat(processorResult, equalTo(model1List))

        val entity2 = listOf(TestEntity(2L, "Test2"))
        val model2 = TestModel(2L, "Test2")
        val expectedList = listOf(model1, model2)

        assertThat(processor.process(entity2), equalTo(expectedList))
    }

    private data class TestEntity(val id: Long, val title: String)
    private inner class TestModel(id: Long, val title: String) : BaseUiModel(id) {
        override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
            if (other !is TestModel) {
                return false
            }

            return id == other.id
                    && title == other.title
        }
    }

    private inner class TestConverter : UiModelConverter<TestEntity, TestModel> {
        override fun toUiModel(entity: TestEntity) = with(entity) {
            TestModel(id, title)
        }
    }

    private inner class TestPaginatedListUiModelProcessor :
        PaginatedListUiModelProcessor<TestEntity, TestModel>(
            TestConverter(),
            compareBy(TestModel::title)
        )

}