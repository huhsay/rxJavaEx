package ch5;

import common.Log;
import okhttp3.*;

import java.io.IOException;

public class HttpGetEx {

    public static void main(String[] args) {
        final String URL_README =
                "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";

        Request request = new Request.Builder()
                .url(URL_README)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(response.body().string());
            }
        });

    }
}
