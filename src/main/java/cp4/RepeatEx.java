package cp4;

import common.OkHttpHelper;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class RepeatEx {
    public static void main(String[] args){
        String serverUrl = "https://api.github.com/zen";

        Observable.timer(2, TimeUnit.SECONDS)
                .map(val -> serverUrl)
                .map(OkHttpHelper::get)
                .repeat()
                .subscribe(System.out::println);
        try{
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
