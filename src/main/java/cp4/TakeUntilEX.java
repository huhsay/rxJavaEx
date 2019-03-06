package cp4;

import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;

public class TakeUntilEX {
    public static void main(String args[]) {
        String[] data1 = {"1", "2", "3", "4", "5"};
        String[] data2 = {"5", "6", "7"};

        Observable.fromArray(data1).zipWith(
                Observable.interval(100L, TimeUnit.MILLISECONDS),
                (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
