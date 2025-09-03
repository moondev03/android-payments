package woowacourse.payments.domain

@JvmInline
value class CardholderName private constructor(
    val value: String,
) {
    init {
        require(value.length in MINIMUM_NAME_LENGTH_VALUE..MAXIMUM_NAME_LENGTH_VALUE) { ERROR_INVALID_LENGTH }
    }

    companion object {
        private const val ERROR_INVALID_FORMAT = "카드 소유자 이름은 영문이어야 합니다."
        private const val ERROR_INVALID_LENGTH = "카드 소유자 이름은 30자를 초과할 수 없습니다."
        private const val MINIMUM_NAME_LENGTH_VALUE = 1
        private const val MAXIMUM_NAME_LENGTH_VALUE = 30

        private val NO_CARD_HOLDER_NAME = CardholderName("UNKNOWN")

        fun from(name: String): CardholderName {
            name.ifBlank { return NO_CARD_HOLDER_NAME }

            val formattedName = name.filterNot { it.isWhitespace() }.uppercase()
            require(formattedName.all { it in 'A'..'Z' }) { ERROR_INVALID_FORMAT }

            return CardholderName(formattedName)
        }
    }
}
