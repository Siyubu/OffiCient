package edu.cmu.officient.ui.users;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.cmu.officient.DBCommunication.JSONProtocol;
import edu.cmu.officient.R;
import edu.cmu.officient.ui.courses.AddCourse;
import edu.cmu.officient.ui.courses.CoursesList;

public class LoginActivity extends AppCompatActivity {

    private Button registrationOpener;
    private Button loginBtn;
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private String message;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        registrationOpener = findViewById(R.id.register);
        loginBtn = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login().execute(username.getText().toString(), password.getText().toString());
            }
        });
        registrationOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AddCourse.class);
                startActivity(intent);
            }
        });
    }


    private class Login extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
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
            httpParams.put("login", "login");
            jsonObject = httpJsonParser.makeHttpRequest(
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
//                try {
//                   // System.out.println(jsonObject.getJSONArray("data"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CoursesList.class);
                startActivity(intent);
            }
            else if(message.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the network", Toast.LENGTH_SHORT).show();
            }
            else if (message.equalsIgnoreCase("no_account")){
                Toast.makeText(context, "You have not registered", Toast.LENGTH_SHORT).show();
            }
            else if (message.equalsIgnoreCase("wrong_password")){
                try {
                    System.out.println(jsonObject.getString("data"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, "Wrong password", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void goStraight(View view) {
        Intent intent = new Intent(this, CoursesList.class);
        startActivity(intent);
    }
}
