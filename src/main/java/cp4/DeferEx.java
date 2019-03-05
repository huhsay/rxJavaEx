package cp4;

import io.reactivex.Observable;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class DeferEx {
    static Iterator<String> colors = Arrays.asList("1", "2", "3", "4").iterator();

    public static void main(String[] args) {
        marbleDiagram();


    }

    public static void marbleDiagram() {
        Callable<Observable<String>> supplier = () -> getObservable();
        Observable<String> source = Observable.defer(supplier);

        source.subscribe(val -> System.out.println(1 + val));
        source.subscribe(val -> System.out.println(2 + val));
        source.subscribe(val -> System.out.println(3 + val));
        source.subscribe(val -> System.out.println(4 + val));
        source.subscribe(val -> System.out.println(5 + val));
        source.subscribe(val -> System.out.println(6 + val));

    }

    private static Observable<String> getObservable() {

        if (colors.hasNext()){
            String color = colors.next();
            return Observable.just(
                    color+"a",
                    color+"b",
                    color+"c");
        }

        return Observable.empty();
    }
}
