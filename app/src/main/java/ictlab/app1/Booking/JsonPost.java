package ictlab.app1.Booking;

import android.os.StrictMode;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JsonPost extends OkHttpClient {
    public void sendData(String date_id, String title, String description, String from, String to, String classroom_id) {
        if (classroom_id.contains("H4")) {
            classroom_id = "1";
        } if(classroom_id.contains("H6")){
            classroom_id = "2";
        }if (classroom_id.contains("KZ2")) {
            classroom_id="3";
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"classroom_id\": \""+classroom_id+"\",\n\t\"date\": \""+date_id+ "\",\n\t\"title\": \""+title+"\",\n\t\"description\": \""+description+"\",\n\t\"from\": \""+from+"\",\n\t\"to\": \""+to+"\"\n}");
        Request request = new Request.Builder()
                .url("")//("http://192.168.2.6:3000/reservations.json") TODO ENTER IP ADDRESS HERE
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "c47172f5-8f46-48d6-8713-7fa66b3c21c8")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                String results = response.body().string();
                System.out.println("Server meld het volgende: " + results);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}