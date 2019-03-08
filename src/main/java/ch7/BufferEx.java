package ch7;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class BufferEx {

    public static void main(String[] args){
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        Observable<String> earlySource = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsted) -> val)
                .take(3);

        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val);

        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val);

        Observable<List<String>> source = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(2,3);

        source.subscribe(Log::it);

        CommonUtils.sleep(1000);
    }
}
