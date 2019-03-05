package cp4;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SwitchMapEx {

    public static void main(String[] args) {
        String[] balls = {"1", "2", "3"};

        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(s -> System.out.println(s+"debug"))
                .switchMap(val -> Observable.interval(50L, TimeUnit.MILLISECONDS)
                        .take(3)
                        .map(notUsed -> val + " diamond"))
                .subscribe(System.out::println);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
