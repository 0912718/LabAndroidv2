package ictlab.app1.Booking;

import android.app.Activity;
import android.os.StrictMode;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */
// controls the login and post for the json
public class JsonPost extends Activity{
    public String accessToken;
    public String clientToken;
    public String uid;
    public String url = "http://145.24.222.187:3000"; //TODO
    public Response response;

    public boolean login(String email, String password) {
        Response response = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{ \"email\": \"" + email + "\",\"password\": \"" + password + "\" }");
        Request request = new Request.Builder()
                .url(url + "/api/v1/auth/sign_in")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            response = client.newCall(request).execute();
            String jsonData = response.body().string();
            System.out.println(jsonData + "lololol" + response.code());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (response.code() == 200) {
            Headers responseHeaders = response.headers();
            //4 = access-token
            accessToken = responseHeaders.value(4);

            //6 = client
            clientToken = responseHeaders.value(6);

            //8 = uid
            uid = responseHeaders.value(8);
            System.out.println("AT = " + accessToken);
            System.out.println("CT = " + clientToken);
            System.out.println("UID = " + uid);

            return true;
        } else {
            return false;
        }
    }

    public boolean sendData(String date_id, String title, String description, String from, String to, String classroom_id) {//, String clientTokens, String accessTokens, String uids) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        System.out.println(classroom_id);
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"classroom_id\": " + classroom_id + ",\n\t\"date\": \"" + date_id + "\",\n\t\"title\": \"" + title + "\",\n\t\"description\": \"" + description + "\",\n\t\"from_block\": \"" + from + "\",\n\t\"to_block\": \"" + to + "\"\n}");
        Request request = new Request.Builder()
                .url("http://145.24.222.187:3000/api/v1/reservations")//("http://192.168.2.6:3000/reservations.json") TODO ENTER IP ADDRESS HERE
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "dc6134e9-f850-46b8-a543-e33252b9fded")
                .addHeader("access-token", accessToken)
                .addHeader("client", clientToken)
                .addHeader("uid",  uid)
                .build();
        System.out.println("ATpost = " + accessToken);
        System.out.println("CTpost = " + clientToken);
        System.out.println("UIDpost = " + uid);

        try {
            response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();

        }
        if (response.code() == 200) {
            return true;
        }
        else{
            System.out.println("Server zegt: " + response.toString());
            return false;
        }
    }
}