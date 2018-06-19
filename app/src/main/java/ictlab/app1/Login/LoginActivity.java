package ictlab.app1.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ictlab.app1.Booking.JsonPost;
import ictlab.app1.R;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */
//login
public class LoginActivity extends AppCompatActivity {
    public EditText email, password;
    JsonPost post = new JsonPost();
    public String accessToken, clientToken, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (!wifi.isWifiEnabled()) {
            checkWifi();
        } else {
            setContentView(R.layout.activity_login);
        }
    }

    public void checkWifi() {
        final WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("The app does not work without Wi-Fi");
        dialog.setMessage("Do you want to turn on Wi-Fi?");
        dialog.setPositiveButton("Turn me on", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                wifi.setWifiEnabled(true);
                System.out.println("clicked yes");
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        wifi.setWifiEnabled(false);
                        System.exit(0);
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    public void loginButtonClick(View view) {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        if (post.login(emailString, passwordString)) {
            Intent myIntent = new Intent(LoginActivity.this, afterLoginSuccess.class);
            clientToken = post.clientToken;
            accessToken = post.accessToken;
            uid = post.uid;
            myIntent.putExtra("clientToken", clientToken);
            myIntent.putExtra("accessToken", accessToken);
            myIntent.putExtra("uid", uid);
            LoginActivity.this.startActivity(myIntent);
            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, "Failed to login, please try again", Toast.LENGTH_LONG).show();

        }
    }
}

