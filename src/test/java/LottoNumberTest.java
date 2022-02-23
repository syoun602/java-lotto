import domain.LottoNumber;
import domain.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호 범위가 아니면 예외")
    void lottoNumberRangeException(int source) {
        assertThatThrownBy(() -> LottoNumber.create(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호 범위가 아니면 예외")
    void lottoNumberCreate(int source) {
        assertDoesNotThrow(() -> LottoNumber.create(source));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복시 예외")
    void bonusNumberDuplicateException() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(1, 2, 3, 4, 5, 6)
                .mapToObj(LottoNumber::create)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> LottoNumber.createBonus(6, new LottoNumbers(inputLottoNumbers)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
