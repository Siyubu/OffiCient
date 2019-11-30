package edu.cmu.officient.ui.users;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.cmu.officient.R;
import edu.cmu.officient.ui.MainActivity;
import edu.cmu.officient.ui.courses.CoursesList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button registrationOpener = findViewById(R.id.register);
        registrationOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goStraight(View view) {
        Intent intent = new Intent(this, CoursesList.class);
        startActivity(intent);
    }
}
