package ch7;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class WindowEx {

    public static void main(String[] args){
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        Observable<String> earlySource = Observable.fromArray(data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val);


        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val);


        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val);

        Observable.concat(earlySource, middleSource, lateSource)
                .window(3).subscribe(stringObservable -> {
            Log.dt("New Observagle Started!!");
            stringObservable.subscribe(Log::it);
        });

        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }
}
