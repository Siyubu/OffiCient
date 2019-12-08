package edu.cmu.officient.ui.users;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import edu.cmu.officient.DBCommunication.CheckInternetConnection;
import edu.cmu.officient.R;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;

public class RegistrationActivity extends AppCompatActivity {
    private EditText username;
    private  EditText password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);
        Button completeRegistration = findViewById(R.id.register);
        completeRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckInternetConnection.getInstance().isAvailable()){
                    StandardRequestTask task = RequestTaskFactory.getTask(progressBar, findViewById(android.R.id.content).getRootView(), RegistrationActivity.this, null, "signup");
                    if (task != null)
                        task.execute("signup",username.getText().toString(),password.getText().toString());
                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Unable to connect to the network", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
