package woowacourse.payments.domain

@JvmInline
value class CardPassword private constructor(
    val value: String,
) {
    init {
        require(value.length == VALID_LENGTH) { ERROR_INVALID_LENGTH }
        require(value.all { it.isDigit() }) { ERROR_INVALID_FORMAT }
    }

    companion object {
        private const val ERROR_INVALID_LENGTH = "카드 비밀번호는 4자리여야 합니다."
        private const val ERROR_INVALID_FORMAT = "카드 비밀번호는 숫자로만 구성되어야 합니다."
        private const val VALID_LENGTH = 4

        fun from(password: String): CardPassword {
            val formattedPassword = password.filterNot { it.isWhitespace() }
            return CardPassword(formattedPassword)
        }
    }
}
