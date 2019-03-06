package cp4;

import common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AllEx {

    public static void main(String args[]){
        String[] data = {"1", "2", "3", "4"};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getColor)
                .all(Shape.BALL::equals);
        source.subscribe(System.out::println);
    }
}
