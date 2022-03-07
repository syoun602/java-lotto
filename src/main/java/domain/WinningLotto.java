package domain;

public class WinningLotto {

    private static final String INVALID_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoNumber createBonus(int inputBonusNumber, LottoNumbers winningNumbers) {
        LottoNumber bonusNumber = LottoNumber.getInstance(inputBonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER);
        }
        return bonusNumber;
    }

    public LottoRank rank(LottoNumbers lottoNumbers) {
        int count = this.lottoNumbers.countDuplicateNumbers(lottoNumbers);
        boolean hasBonus = this.lottoNumbers.contains(bonusNumber);

        return LottoRank.valueOf(count, hasBonus);
    }
}
