package ir.madeinlobby.milmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EmailListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmailAdapter adapter = new EmailAdapter(this, Email.getAllEmails());
        recyclerView.setAdapter(adapter);

    }

    public void sendEmailClicked(View view) {
        Intent intent= new Intent(this, SendMail.class);
        startActivity(intent);
    }
}