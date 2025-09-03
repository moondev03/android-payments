package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@JvmInline
value class CardNumber(
    val value: String,
) {
    init {
        require(value.length == REQUIRE_CARD_NUMBER_LENGTH) { ERROR_INVALID_LENGTH }
    }

    companion object {
        private const val REQUIRE_CARD_NUMBER_LENGTH = 16
        private const val ERROR_INVALID_LENGTH = "카드번호는 16자리여야 합니다."
    }
}

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
        // when & then
        shouldThrow<IllegalArgumentException> { CardNumber(number) }
    }
}
