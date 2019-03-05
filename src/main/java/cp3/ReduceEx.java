package cp3;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ReduceEx {

    public static void main(String[] args){

        String[] balls = {"1", "2", "3", "4"};

        Observable.fromArray(balls).reduce((ball1, ball2) -> ball2 +"("+ball1 + ")").subscribe(System.out::println);

        BiFunction<String, String, String> mergeBalls = (ball1, ball2) -> ball2 + "(" + ball1 + ")";

        Observable.fromArray(balls).reduce(mergeBalls).subscribe(System.out::println);


    }
}
