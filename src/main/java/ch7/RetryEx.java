package ch7;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.concurrent.TimeUnit;

public class RetryEx {
    public static void main(String args[]) {
        String url = "https://api.github.com/zen";
        Observable<String> source = Observable.just(url)
                .map(OkHttpHelper::getT)
                .retry((retryCnt, e) -> {
                    Log.e("rtryCnt " + retryCnt);
                    CommonUtils.sleep(1000);

                    return retryCnt < 5 ? true : false;
                })
                .onErrorReturn(e -> CommonUtils.ERROR_CODE);

        source.subscribe(data -> Log.it("result : " + data));


        Observable<String> source1 = Observable.just(url)
                .map(OkHttpHelper::getT)
                .retryUntil(()->{
                    if(CommonUtils.isNetworkAvailable()){
                        return true;
                    }else{
                        CommonUtils.sleep(1000);
                        return false;
                    }
                })
                .onErrorReturn(e -> CommonUtils.ERROR_CODE);

        source1.subscribe(data -> Log.it("result : " + data));


        // retry when

        Observable.create((ObservableEmitter<String> emitter)->{
            emitter.onError(new RuntimeException("always fails"));
        }).retryWhen(attemps ->{
            return attemps.zipWith(Observable.range(1,3), (n, i) -> i)
                    .flatMap(i -> {
                        Log.d("delay retry by " + i + " seconds");
                        return Observable.timer(i, TimeUnit.SECONDS);
                    })
                    ;
        }).blockingForEach(Log::d);
    }
}
