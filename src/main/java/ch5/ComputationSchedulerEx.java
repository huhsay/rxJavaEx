package ch5;


import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableFromArray;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class ComputationSchedulerEx {

    public static void main(String[] args){
        String[] orgs = {"1", "2", "3"};
        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (a,b)->a);

        source.map(item -> "<<" + item+ ">>")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);

        source.map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);

        CommonUtils.sleep(500);
    }
}
