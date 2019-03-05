package cp3;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;

public class FilterEx {
    public static void main(String[] args){
        String[] objs = { "1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};

        Observable.fromArray(objs).filter(obs -> obs.endsWith("CIRCLE")).subscribe(System.out::println);
        Predicate<String> filterCircle = obj -> obj.endsWith("CIRCLE");

        Integer[] data = { 100, 20, 1, 3, 99, 50};
        Observable<Integer> source = Observable.fromArray(data)
                .filter(number -> number % 2 == 0);
        source.subscribe(System.out::println);


        Observable.fromArray(data).take(3).subscribe(System.out::println);

        System.out.println("~~~~");

        Integer[] numbers = { 100, 200, 300, 400, 500};

        Observable.fromArray(numbers).first(-1).subscribe(System.out::println);
        Observable.fromArray(numbers).last(-1).subscribe(System.out::println);
        Observable.fromArray(numbers).take(3).subscribe(System.out::println);
        Observable.fromArray(numbers).takeLast(3).subscribe(System.out::println);
        Observable.fromArray(numbers).skip(3).subscribe(System.out::println);
        Observable.fromArray(numbers).skipLast(3).subscribe(System.out::println);



    }
}
