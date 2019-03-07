package ch5;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class flip {

    public static void main(String args[]){
        String[] objs = {"1-5", "2-T", "3_T"};
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> Log.v("Original data = " + data))
                .subscribeOn(Schedulers.newThread())
                //.observeOn(Schedulers.newThread())
                .map(Shape::flip);

        source.subscribe(Log::i);
        CommonUtils.sleep(500);
    }
}
