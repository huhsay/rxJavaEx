package cp4;

import io.reactivex.Observable;
import sun.misc.OSEnvironment;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmbEx {
    public static void main(String args[]) {
        String[] data1 = {"1", "2", "3"};
        String[] data2 = {"2-R", "4-r"};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete(() -> System.out.println("Observable #1 : onComplete()")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> System.out.println("Observable #2 : onComplete()"))
        );

        Observable.amb(sources)
                .doOnComplete(() -> System.out.println("Result : onComplete()"))
                .subscribe(System.out::println);
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
