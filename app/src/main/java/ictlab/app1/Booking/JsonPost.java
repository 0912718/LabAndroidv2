package ictlab.app1.Booking;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JsonPost{
    public void sendData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"classroom_id\": \"1\",\n\t\"date\": \"27-05-2018\",\n\t\"title\": \"AndroidServerTest2\",\n\t\"description\": \"AndroidServerTEst2\",\n\t\"from\": \"9\",\n\t\"to\": \"10\"\n}");
        Request request = new Request.Builder()
                .url("http://192.168.2.9:3000/reservations.json")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "c47172f5-8f46-48d6-8713-7fa66b3c21c8")
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}