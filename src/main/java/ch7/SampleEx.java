package ch7;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class SampleEx {
    public static void main(String[] args) {
        String[] data = {"1", "7", "2", "3", "6"};

        // 시간 측정용
        CommonUtils.exampleStart();

        Observable<String> earlySource = Observable.fromArray(data)
                .take(4)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (a,b) -> a);

        Observable<String> lateSource = Observable.just(data[4])
                .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);

        Observable<String> source = Observable.concat(earlySource, lateSource)
                .sample(300L, TimeUnit.MILLISECONDS, true);

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }
}
