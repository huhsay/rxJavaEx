package cp4;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupByEx {

    public static void main(String[] args){
        String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(string -> getShape(string));

        source.subscribe(obj -> obj.subscribe(val -> System.out.println(obj.getKey()+val)));
    }

    public static String getShape(String obj){
        if(obj == null || obj.equals("")) return "NO-SHAPE";
        if(obj.endsWith("-H")) return "HEXAGON";
        if(obj.endsWith("-O")) return "OCTAGON";
        if(obj.endsWith("-R")) return "RECTANGLE";
        if(obj.endsWith("-T")) return "TRIANGLE";
        if(obj.endsWith("-DIA")) return "DIAMOND";
        return "Ball";
    }



}
