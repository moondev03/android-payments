package woowacourse.payments.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardExpirationDateTest {
    @Test
    fun `만료일을 생성한다`() {
        // given
        val month = 10
        val year = 2025

        // when
        val actual = CardExpirationDate.of(month = month, year = year)

        // then
        assertSoftly(actual.value) {
            this.year shouldBe year
            this.monthValue shouldBe month
        }
    }

    @Test
    fun `만료일의 월이 1 ~ 12 사이가 아닌 경우 예외가 발생한다`() {
        // given
        val month = 13
        val year = 2025

        // when
        val actual =
            shouldThrow<IllegalArgumentException> { CardExpirationDate.of(month, year) }.message
        val expected = "월은 1~12 사이여야 합니다."

        // then
        actual shouldBe expected
    }

    @Test
    fun `만료일은 과거의 날짜인 경우 예외가 발생한다`() {
        // given
        val month = 8
        val year = 2025

        // when
        val actual =
            shouldThrow<IllegalArgumentException> { CardExpirationDate.of(month, year) }.message
        val expected = "만료일은 현재 이후 월이어야 합니다."

        // then
        actual shouldBe expected
    }
}
