package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardholderNameTest {
    @Test
    fun `카드 소유자를 생성한다`() {
        // given
        val name = "CREW"

        // when
        val actual = CardholderName.from(name)

        // then
        actual.value shouldBe name
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "crew!",
            "크루",
            "123",
        ],
    )
    fun `카드 소유자 이름은 영문이 아니라면 예외가 발생한다`(name: String) {
        // when
        val actual = shouldThrow<IllegalArgumentException> { CardholderName.from(name) }.message
        val expected = "카드 소유자 이름은 영문이어야 합니다."

        // then
        actual shouldBe expected
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "    "])
    fun `빈 문자열인 경우 기본 카드 소유자를 반환한다`(name: String) {
        // when
        val actual = CardholderName.from(name).value
        val expected = "UNKNOWN"

        // then
        actual shouldBe expected
    }

    @Test
    fun `카드 소유자 이름은 30자를 초과할 수 없다`() {
        // given
        val name = "A".repeat(31)

        // when
        val actual = shouldThrow<IllegalArgumentException> { CardholderName.from(name) }.message
        val expected = "카드 소유자 이름은 30자를 초과할 수 없습니다."

        // then
        actual shouldBe expected
    }
}
