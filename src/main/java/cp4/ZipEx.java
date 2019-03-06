package cp4;

import common.Shape;
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;

public class ZipEx {

    public static void main(String[] args) {
        String[] shapes = {"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix
        );

        source.subscribe(System.out::println);

        Observable.zip(
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                Observable.just("RED", "GREEN", "BLUE"),
                (i, value) -> value)
                .subscribe(System.out::println);

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
