package ir.madeinlobby.milmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonTapped(View view) {
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        Gonnect.getData("http://spneshaei.com/mil/getEmails.php?username="
                + username + "&password=" + password, this, new Gonnect.ResponseSuccessListener() {
            @Override
            public void responseRecieved(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.equals("invalid-user")) {
                            Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
                        } else {
                            ArrayList<Email> newEmails = new Gson().fromJson(response, new TypeToken<ArrayList<Email>>() {
                            }.getType());
//                            Email.setAllEmails(newEmails);
                            Email.getAllEmails().clear();
                            Email.getAllEmails().addAll(newEmails);
                            // adapter.notifyDatasetChanged()
                            Intent intent = new Intent(MainActivity.this, EmailListActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }, new Gonnect.ResponseFailureListener() {
            @Override
            public void responseFailed(IOException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Error in loading", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}