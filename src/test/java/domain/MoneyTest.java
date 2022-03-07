package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009})
    @DisplayName("10 단위가 아니면 예외 발생")
    void moneyExceptionNotDividable10(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 10 단위로 나누어 떨어져야 합니다.");

    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 400, 700, 900, 950, 990})
    @DisplayName("최소 로또 구매 비용 미만 예외")
    void moneyUnderLottoTicketPriceException(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 1000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("돈 정상 생성")
    void moneyInsert() {
        assertDoesNotThrow(() -> new Money(1500));
    }

    @ParameterizedTest
    @ValueSource(ints = {1100, 2000, 3300, 4000, 5500, 6000, 7000, 8000, 9900, 10000})
    @DisplayName("입력 금액으로 생성할 수 있는 로또 개수 확인")
    void checkPurchasableNumber(int amount) {
        int actual = new Money(amount).getPurchasableNumber();
        int expected = amount / 1000;

        assertThat(actual).isEqualTo(expected);
    }
}
