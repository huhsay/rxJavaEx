package cp4;

import common.Shape;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ConbineLatest {
    public static void main(String args[]) {
        String[] data1 = {"6", "7", "4", "2"};
        String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};

        Observable<String> source = Observable.combineLatest(
                Observable.fromArray(data1)
                        .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getColor(shape)),
                Observable.fromArray(data2)
                        .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getSuffix(shape)),
                (v1, v2) -> v1 + v2
        );

        source.subscribe(System.out::println);

        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
