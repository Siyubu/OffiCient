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
import edu.cmu.officient.DBCommunication.CheckInternetConnection;
import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;

public class RegistrationActivity extends AppCompatActivity {

    private Context context;
    private  Button completeRegistration;
    private Toolbar toolbar;
    private EditText username;
    private  EditText password;
    private ProgressBar progressBar;

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
                if(CheckInternetConnection.getInstance().isAvailable()){
                    new Register().execute("signup",username.getText().toString(),password.getText().toString());
                }
                else{
                    Toast.makeText(context, "Unable to connect to the network", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class Register extends AsyncTask <String, String, String> {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
            String[] attributes = new String[]{"signup", "andrewId", "password"};
            RequestData requestData = new RequestData( context,"http://gamfruits.com/officient_api/functions.php", attributes, args);
            jsonObject = requestData.getResponse();
            System.out.println(jsonObject);
            try {
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                message = "error";
            }
            return message;
        }

        protected void onPostExecute(String result){

            progressBar.setVisibility(View.GONE);
            System.out.println(result);
            if (result.equalsIgnoreCase("success")){
                Intent intent = new Intent(RegistrationActivity.this, SuccessfulRegistration.class);
                startActivity(intent);
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the network", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("already_registered")){
                Toast.makeText(context, "User is already registered", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                Toast.makeText(context, "Problem with sql query", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
