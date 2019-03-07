package ch5;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenWeatherMapV1 {
    private static final String API_KEY = "183cfe5aeba6e17b8c919f182a05c640";
    private static final String URL =
            "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";

    public void run() {
        Observable<String> source = Observable.just(URL + API_KEY)
                .map(OkHttpHelper::get)
                .subscribeOn(Schedulers.io());

        Observable<String> temperature = source.map(this::parseTemperature);
        Observable<String> city = source.map(this::pareseCityName);
        Observable<String> country = source.map(this::pareseCountry);
        CommonUtils.sleep(3000);

        Observable.concat(temperature, city, country)
                .observeOn(Schedulers.newThread())
                .subscribe(Log::i);

        CommonUtils.sleep(3000);
    }

    private String parseTemperature(String json){
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }
    private String pareseCityName(String json){
        return parse(json, "\"name\":\"[a-zA-Z]*\"");
    }
    private String pareseCountry(String json){
        return parse(json, "\"country\":\"[a-zA-Z]*\"");
    }

    private String parse(String json, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(json);
        if(match.find()){
            return match.group();
        }

        return "N/A";
    }

    public static void main(String[] args){
        OpenWeatherMapV1 demo = new OpenWeatherMapV1();
        demo.run();
    }

}
