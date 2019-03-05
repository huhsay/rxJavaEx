package cp4;

import io.reactivex.Observable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerEx {
    public static void main(String[] args){
        Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map(aLong -> {
                    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());})
                .subscribe(System.out::println);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Observable.range(1, 10).filter(integer -> integer % 2 == 0).subscribe(System.out::println);


    }
}
