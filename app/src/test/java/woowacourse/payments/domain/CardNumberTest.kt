package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTest {
    @Test
    fun `카드번호는 16자리이다`() {
        // given
        val cardNumber = "1234123412341234"

        // when
        val actual = CardNumber(cardNumber).value.length
        val expected = 16

        // then
        actual shouldBe expected
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "12341234123412341",
            "123412341234123",
            "",
        ],
    )
    fun `카드번호는 16자리가 넘으면 예외가 발생한다`(number: String) {
        // when
        val actual = shouldThrow<IllegalArgumentException> { CardNumber(number) }.message
        val expected = "카드번호는 16자리여야 합니다."

        // then
        actual shouldBe expected
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "abcdefghijklmnop",
            "                ",
            "123412341234123a",
            "12341234!2341234",
        ],
    )
    fun `카드번호는 숫자가 아닌 문자가 포함될 경우 예외가 발생한다`(number: String) {
        // when
        val actual = shouldThrow<IllegalArgumentException> { CardNumber(number) }.message
        val expected = "카드번호는 숫자로만 구성되어야 합니다."

        // then
        actual shouldBe expected
    }
}
