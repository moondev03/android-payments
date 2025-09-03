package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardPasswordTest {
    @Test
    fun `카드 비밀번호를 생성한다`() {
        // given
        val password = "1234"

        // when
        val actual = CardPassword.from(password).value

        // then
        actual shouldBe password
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "123",
            "12345",
            "",
            "    ",
            "1  5",
            "123 ",
        ],
    )
    fun `카드 비밀번호는 공백을 제외한 4자리가 아닐 경우 예외가 발생한다`(password: String) {
        // when
        val actual = shouldThrow<IllegalArgumentException> { CardPassword.from(password) }.message
        val expected = "카드 비밀번호는 4자리여야 합니다."

        // then
        actual shouldBe expected
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "123!",
            "123a",
        ],
    )
    fun `카드 비밀번호는 숫자가 아닌 문자가 포함될 경우 예외가 발생한다`(password: String) {
        // when
        val actual = shouldThrow<IllegalArgumentException> { CardPassword.from(password) }.message
        val expected = "카드 비밀번호는 숫자로만 구성되어야 합니다."

        // then
        actual shouldBe expected
    }
}
