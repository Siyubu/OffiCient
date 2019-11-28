package edu.cmu.officient.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.officient.R;

public class ProfileEditActivity extends AppCompatActivity {
    TextView title;
    TextView name;
    TextView email;
    TextView altEmail;
    TextView pswd;
    EditText entName;
    EditText entEmail;
    EditText entAltEmail;
    EditText entPswd;
    Button submit;
    Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        title=(TextView) findViewById(R.id.editName);
        name=(TextView)findViewById(R.id.textName);
        email=(TextView)findViewById(R.id.textEmail);
        altEmail=(TextView) findViewById(R.id.textAltEmail);
        pswd=(TextView)findViewById(R.id.textpaswd);
        entName=(EditText)findViewById(R.id.changeName);
        entEmail=(EditText)findViewById(R.id.changeEmail);
        entAltEmail=(EditText)findViewById(R.id.enterAltEmail);
        entPswd=(EditText) findViewById(R.id.changePswd);
        submit=(Button) findViewById(R.id.sub);
        cancel=(Button) findViewById(R.id.canc);
    }
}
