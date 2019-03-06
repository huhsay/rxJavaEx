package cp4;

import io.reactivex.Observable;
import io.reactivex.functions.Action;

import java.util.concurrent.TimeUnit;

public class ConcatEx {

    public static void main(String[] args){
        Action onCompleteAction = () -> System.out.println("onComplete()");

        String[] data1 = {"1", "3","5"};
        String[] data2 = {"2", "4", "6"};
        Observable<String> source1 = Observable.fromArray(data1)
                .doOnComplete(onCompleteAction);
        Observable<Integer> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue);

        Observable<String> source3 = Observable.zip(
                Observable.interval(100L, TimeUnit.MILLISECONDS),
                Observable.fromArray(data2),
                (notUse, val) -> val)
                .take(data2.length)
                .doOnComplete(onCompleteAction);

        Observable<String> source = Observable.concat(source1, source3)
                .doOnComplete(onCompleteAction);

        source.subscribe(System.out::println);

        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
