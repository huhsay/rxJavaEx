package ch5;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static common.CommonUtils.GITHUB_ROOT;
import static common.CommonUtils.exampleComplete;

public class CallBackHeaven {
    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_hell";
    public static void main(String[] args){

        CommonUtils.exampleStart();
        Observable<String> source = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get)
                .concatWith(Observable.just(SECOND_URL)
                .map(OkHttpHelper::get));

        source.subscribe(Log::it);
        CommonUtils.sleep(5000);

        Observable<String> first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);
        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        Observable.zip(first, second, (val1, val2) -> ("\n>> " + val1 + "\n>> " + val2))
                .subscribe(Log::it);

        CommonUtils.sleep(5000);
    }
}
