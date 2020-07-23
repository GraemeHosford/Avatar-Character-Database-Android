package graeme.hosford.avatarcharacterdatabase.repo.layer.common.pagination

import org.junit.Assert.assertEquals
import org.junit.Test

class PagingUtilsTest {

    @Test
    fun getOffset_returnsCorrectPageOffset() {
        val offset1 = PagingUtils.getOffset(1, 25)
        assertEquals(0, offset1)

        val offset2 = PagingUtils.getOffset(3, 34)
        assertEquals(68, offset2)
    }

}