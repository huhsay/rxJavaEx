package cp3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class MapEx {

    public static void main(String[] args) {
        String[] balls = {"1", "2", "3"};
        Observable<String> source = Observable.fromArray(balls);


        Function<String, Integer> ballToIndex = ball -> {
            switch (ball) {
                case "RED":
                    return 1;
                case "YELLOW":
                    return 2;
                case "GREEN":
                    return 3;
                case "BLUE":
                    return 4;
                default:
                    return -1;
            }
        };
        Function<String, Observable<String>> getDoubleDiamonds = ball -> Observable.just(ball + "diamond", ball + "diamond2");


        source.map(ball -> ball + "Diamond").subscribe(System.out::println);
        source.map(MapEx::getDiamond).subscribe(System.out::println);

        String[] ball = {"RED", "YELLOW", "GREEN", "BLUE"};
        Observable.fromArray(ball).map(ballToIndex).subscribe(System.out::println);
        Observable.fromArray(ball).flatMap(getDoubleDiamonds).subscribe(System.out::println);

        Observable.fromArray(balls).flatMap(s -> Observable.just(s + "1", s + "2")).subscribe(System.out::println);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Gugudan Input:");
        int dan = Integer.parseInt(scanner.nextLine());

        Observable.range(1, 9).subscribe(row -> System.out.println(dan + " * " + row + " = " + dan * row));


        Function<Integer, Observable<String>> gugudan = num -> Observable
                .range(1, 9)
                .map(row -> num + " * " + row + " = " + num * row);

        Observable<String> s = Observable.just(dan).flatMap(gugudan);
        s.subscribe(System.out::println);

        System.out.println("~~~~~~~~~~");

        Observable<String> s2 = Observable.just(dan)
                .flatMap(num -> Observable.range(1, 9)
                        .map(row -> num + " * " + row + " = " + dan * row)
                );

        s2.subscribe(System.out::println);


        System.out.println("~~~~~~~~~~");

        Observable.just(dan)
                .flatMap(i -> Observable.range(1,2),
                        (i, j) -> i + " * " + j + " = " + i*j).subscribe(System.out::println);



    }


    public static String getDiamond(String ball) {
        return ball + "Diamond";
    }
}
