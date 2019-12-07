/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.cmu.officient.DBCommunication.CheckInternetConnection;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.ui.listener.AddCourseListener;
import edu.cmu.officient.ui.users.LoginActivity;
import edu.cmu.officient.ui.util.NavigationInitializer;

public class MainActivity extends AppCompatActivity {
    private ApplicationManager manager = ApplicationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the Internet Connection class
        CheckInternetConnection checker = CheckInternetConnection.getInstance();
        checker.setContext(getApplicationContext()); // Set it to the application context so it doesn't leak

          // FloatingActionButton scanCode = findViewById(R.id.scan_code);
         // scanCode.setOnClickListener(new AddCourseListener(this));

        if (manager.getLoggedInUser(this) == null) {
            // If user not logged in, we have them go login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(R.layout.activity_main);

            BottomNavigationView navView = findViewById(R.id.nav_view);
            NavigationInitializer navInitializer = new NavigationInitializer(this);
            navInitializer.setUpBottomNavigationBar(navView);
        }
    }
}
