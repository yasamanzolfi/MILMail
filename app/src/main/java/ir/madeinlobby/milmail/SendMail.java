package ir.madeinlobby.milmail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendMail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
    }

    public void sendPressed(View view) {
        final EditText txtSubject = findViewById(R.id.subject);
        if (txtSubject.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("Subject Field Missed")
                    .setMessage("Subject field is empty. Are you sure you want to send the email?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            EditText receiverEmail = findViewById(R.id.receiver);
                            EditText body = findViewById(R.id.txtBody);
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("message/rfc822");
                            i.putExtra(Intent.EXTRA_EMAIL  , new String[] { receiverEmail.getText().toString() });
                            i.putExtra(Intent.EXTRA_SUBJECT, txtSubject.getText().toString());
                            i.putExtra(Intent.EXTRA_TEXT   , body.getText().toString());
                            try {
                                startActivity(Intent.createChooser(i, "Send mail..."));
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(SendMail.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            EditText receiverEmail = findViewById(R.id.receiver);
            EditText body = findViewById(R.id.txtBody);
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , receiverEmail.getText().toString());
            i.putExtra(Intent.EXTRA_SUBJECT, txtSubject.getText().toString());
            i.putExtra(Intent.EXTRA_TEXT   , body.getText().toString());
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(SendMail.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}