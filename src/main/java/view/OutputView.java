package view;

import domain.*;
import util.ProfitFormatter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String BLANK = "";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printPurchasedLottoTicketNumber(PurchaseCount purchaseCount) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n수동으로 ").append(purchaseCount.getManualCount())
                .append("장, 자동으로 ").append(purchaseCount.getAutoCount())
                .append("개를 구매했습니다.");

        System.out.println(stringBuilder);
    }

    public static void printPurchasedLottoTickets(Lottos lottos) {
        for (LottoNumbers numbers : lottos.getLottos()) {
            System.out.println(numbers.getLottoNumbers());
        }
        System.out.println();
    }

    public static void printWinningStat(WinningStat winningStat, double profit) {
        List<LottoRank> lottoRanks = LottoRank.valuesWithPrize();
        Collections.reverse(lottoRanks);

        System.out.println("\n당첨 통계");
        System.out.println("--------");
        System.out.print(createStatView(winningStat.getStat(), lottoRanks));
        System.out.println("총 수익률은 " + ProfitFormatter.apply(profit) + "입니다.");
    }

    private static String createStatView(Map<LottoRank, Integer> statistics, List<LottoRank> lottoRanks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoRank lottoRank : lottoRanks) {
            stringBuilder.append(lottoRank.getMatchNumber()).append("개 일치")
                    .append(checkSecond(lottoRank))
                    .append(" (").append(lottoRank.getPrize()).append(") - ")
                    .append(statistics.get(lottoRank)).append("개\n");
        }

        return stringBuilder.toString();
    }

    private static String checkSecond(LottoRank lottoRank) {
        if (LottoRank.SECOND == lottoRank) {
            return ", 보너스 볼 일치";
        }
        return BLANK;
    }
}
