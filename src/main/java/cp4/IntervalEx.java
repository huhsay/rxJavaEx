package cp4;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class IntervalEx {


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(data -> (data + 1) * 100)
                .take(5)
                .subscribe(target -> System.out.println(System.currentTimeMillis()-startTime + " | " +target));

        try {Thread.sleep(1000);}
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
