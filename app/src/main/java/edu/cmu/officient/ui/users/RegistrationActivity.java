package edu.cmu.officient.ui.users;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.cmu.officient.DBCommunication.CheckInternetConnection;
import edu.cmu.officient.DBCommunication.JSONProtocol;
import edu.cmu.officient.R;

public class RegistrationActivity extends AppCompatActivity {

    private Context context;
    private  Button completeRegistration;
    private Toolbar toolbar;
    private EditText username;
    private  EditText password;
    private ProgressBar progressBar;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_registration);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);

        completeRegistration = findViewById(R.id.register);
        completeRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(new CheckInternetConnection(context).isInternetAvailable()){
                    new Register().execute(username.getText().toString(),password.getText().toString());
                }
                else{
                    Toast.makeText(context, "Unable to connect to the network", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private class Register extends AsyncTask <String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            JSONProtocol httpJsonParser = new JSONProtocol();
            Map<String, String> httpParams = new HashMap<>();
            httpParams.put("andrewId",args[0]);
            httpParams.put("password", args[1]);
            httpParams.put("signup", "signup");
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    "http://gamfruits.com/officient_api/functions.php", "POST", httpParams);

            System.out.println(jsonObject);
            try {
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                message = "error";
            }
            return null;

        }


        protected void onPostExecute(String result){

            progressBar.setVisibility(View.GONE);
            System.out.println(message);
            if (message.equalsIgnoreCase("success")){
                Intent intent = new Intent(RegistrationActivity.this, SuccessfulRegistration.class);
                startActivity(intent);
            }
            else if(message.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the network", Toast.LENGTH_SHORT).show();
            }
            else if (message.equalsIgnoreCase("already_registered")){
                Toast.makeText(context, "User is already registered", Toast.LENGTH_SHORT).show();
            }
            else if (message.equalsIgnoreCase("failed")){
                Toast.makeText(context, "Problem with sql query", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
