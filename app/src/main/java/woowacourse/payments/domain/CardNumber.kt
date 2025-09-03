package woowacourse.payments.domain

@JvmInline
value class CardNumber(
    val value: String,
) {
    init {
        require(value.length == REQUIRE_CARD_NUMBER_LENGTH) { ERROR_INVALID_LENGTH }
        require(value.all { it.isDigit() }) { ERROR_INVALID_FORMAT }
    }

    companion object {
        private const val REQUIRE_CARD_NUMBER_LENGTH = 16
        private const val ERROR_INVALID_LENGTH = "카드번호는 16자리여야 합니다."
        private const val ERROR_INVALID_FORMAT = "카드번호는 숫자로만 구성되어야 합니다."
    }
}
