package domain.strategy;

import domain.LottoNumbers;
import domain.Money;
import domain.PurchaseCount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ManualLottoNumberStrategyTest {

    @Test
    @DisplayName("수동 생성할 로또 수가 입력 값과 일치하지 않을 경우 에러")
    void manualLottoCountMismatchException() {
        List<List<Integer>> numbers = List.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6));
        PurchaseCount purchaseCount = PurchaseCount.from(new Money(4000), 1);
        assertThatThrownBy(() -> new ManualLottoNumberStrategy(numbers).generate(purchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력 값이 구매하려는 로또의 수와 일치하지 않습니다");
    }

    @Test
    @DisplayName("수동 생성된 로또 번호는 6자리")
    void manuallyGeneratedLottoNumbersSize() {
        List<List<Integer>> numbers = new ManualLottoNumberStrategy(List.of(List.of(1, 2, 3, 4, 5, 6)))
                .generate(PurchaseCount.from(new Money(1000), 1)).stream()
                .map(LottoNumbers::getLottoNumbers)
                .collect(Collectors.toList());

        assertThat((int) numbers.stream()
                .mapToLong(List::size).sum()).isEqualTo(6);
    }

    @Test
    @DisplayName("수동 생성된 로또 번호 정렬")
    void sortManuallyGeneratedLottoNumbers() {
        List<List<Integer>> numbers = new ManualLottoNumberStrategy(List.of(List.of(6, 2, 3, 5, 1, 8)))
                .generate(PurchaseCount.from(new Money(1000), 1)).stream()
                .map(LottoNumbers::getLottoNumbers)
                .collect(Collectors.toList());

        List<Integer> flatNumbers = numbers.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Assertions.assertThat(flatNumbers).containsExactly(1, 2, 3, 5, 6, 8);
    }
}