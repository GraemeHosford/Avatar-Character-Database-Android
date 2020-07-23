package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import graeme.hosford.avatarcharacterdatabase.common.uimodel.BaseUiModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class BaseUiModelTest {

    private lateinit var model: TestBaseUiModel

    @Test
    fun baseUiModel_returnsId_asHashcode() {
        model = TestBaseUiModel(79L)
        assertEquals(79, model.id)
    }

    @Test
    fun baseUiModel_equals_returnsFalse_whenOther_isNull() {
        model = TestBaseUiModel()
        assertFalse(model.equals(null))
    }

    @Test
    fun baseUiModel_equals_returnsFalse_whenOther_doesNotExtendBaseUiModel() {
        model = TestBaseUiModel()
        assertFalse(model.equals(TestClass()))
    }

    private inner class TestClass

    private inner class TestBaseUiModel(id: Long = 1L) : BaseUiModel(id) {
        override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
            /* Do nothing here as it is up to child classes of BaseUiModel to implement
            * this function */
            return true
        }
    }

}