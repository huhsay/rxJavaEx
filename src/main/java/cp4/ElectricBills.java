package cp4;

import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;

public class ElectricBills {

    public static void main(String[] args){


        String[] data = {
                "100",
                "300",
                "400",
                "410"
        };

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                            if (val <= 200) return 910;
                            if (val <= 400) return 1600;
                            return 7300;
                        }
                );

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    double series1 = Math.min(200, val) * 93.3;
                    double series2 = Math.min(200, Math.max(val-200, 0)) * 187.9;
                    double series3 = Math.max(val-400, 0) * 280.65;
                    return (int)(series1 + series2 + series3);
                });

        Observable<Pair<String,Integer>> source = Observable.zip(
                basePrice,
                usagePrice,
                Observable.fromArray(data),
                (v1, v2, i) -> Pair.of(i, v1 + v2)
        );

        source.map(val -> Pair.of(val.getLeft(), new DecimalFormat("#,###").format(val.getRight())))
                .subscribe(val -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Usage: " + val.getLeft() + "kWh => ");
                    sb.append("Price: " + val.getRight() + "원");
                    System.out.println(sb);
                });
    }
}
