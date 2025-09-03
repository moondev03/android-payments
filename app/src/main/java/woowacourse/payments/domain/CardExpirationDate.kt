package woowacourse.payments.domain

import java.time.YearMonth

@JvmInline
value class CardExpirationDate private constructor(
    val yearMonth: YearMonth,
) {
    init {
        require(validFutureOrCurrentMonth(yearMonth)) { ERROR_INVALID_DATE }
    }

    private fun validFutureOrCurrentMonth(
        yearMonth: YearMonth,
        now: YearMonth = YearMonth.now(),
    ): Boolean = !yearMonth.isBefore(now)

    companion object {
        private const val ERROR_INVALID_DATE = "만료일은 현재 이후 월이어야 합니다."
        private const val ERROR_INVALID_MONTH = "월은 1~12 사이여야 합니다."
        private const val MINIMUM_MONTH_VALUE = 1
        private const val MAXIMUM_MONTH_VALUE = 12
        private const val MAXIMUM_YEAR_VALUE = 99
        private const val YEAR_OFFSET = 2000

        fun of(
            month: Int,
            year: Int,
        ): CardExpirationDate {
            require(month in MINIMUM_MONTH_VALUE..MAXIMUM_MONTH_VALUE) { ERROR_INVALID_MONTH }
            val fullYear = if (year <= MAXIMUM_YEAR_VALUE) YEAR_OFFSET + year else year
            val yearMonth = YearMonth.of(fullYear, month)

            return CardExpirationDate(yearMonth)
        }
    }
}
