package ch7;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;


public class ThrottleFirstEx {

    public static void main(String[] args){
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        Observable<String> earlySource = Observable.fromArray(data)
                .take(1)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val);


        Observable<String> middleSource = Observable.just(data[1])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (val, notUsed) -> val);


        Observable<String> lateSource = Observable.just(data[2], data[3], data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .doOnNext(Log::dt);

        Observable<String> source =
                Observable.concat(earlySource, middleSource, lateSource)
                .throttleFirst(200L, TimeUnit.MILLISECONDS);

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);

    }
}
