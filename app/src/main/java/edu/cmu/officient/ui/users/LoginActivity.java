package edu.cmu.officient.ui.users;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.networktaks.RequestTaskFactory;
import edu.cmu.officient.networktaks.StandardRequestTask;
import edu.cmu.officient.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registrationOpener, loginBtn;
        registrationOpener = findViewById(R.id.register);
        loginBtn = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View root = findViewById(android.R.id.content).getRootView();
                StandardRequestTask task = RequestTaskFactory.getTask(progressBar, root, LoginActivity.this, null, "login",username.getText().toString(), password.getText().toString());
                if (task != null)
                    task.execute("login",username.getText().toString(), password.getText().toString());
            }
        });
        registrationOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
