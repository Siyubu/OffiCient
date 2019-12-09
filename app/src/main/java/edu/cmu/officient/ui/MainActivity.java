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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.cmu.officient.DBCommunication.CheckInternetConnection;
import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.ScannedCodeFactory;
import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.ui.courses.CoursesFragment;
import edu.cmu.officient.ui.qr.QRCodeScanner;
import edu.cmu.officient.ui.users.LoginActivity;
import edu.cmu.officient.ui.util.NavigationInitializer;

public class MainActivity extends AppCompatActivity {
    private ApplicationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the Internet Connection class
        CheckInternetConnection checker = CheckInternetConnection.getInstance();
        checker.setContext(getApplicationContext()); // Set it to the application context so it doesn't leak

        manager = ApplicationManager.getInstance(this);

        if (manager.getLoggedInUser() == null) {
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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment(this)).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QRCodeScanner.QR_CODE_READER && data != null) {
            String output = data.getStringExtra("code");
            ScannedQRCode qrCode = ScannedCodeFactory.loadCode(output);
            if (qrCode != null) {
                switch (manager.processScannedCode(qrCode)) {
                    case RUNNING:
                        Toast.makeText(this, "Monitoring has started for " + qrCode.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case STOPPED:
                        Toast.makeText(this, "Monitoring has stopped for " + qrCode.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case EXPIRED:
                        Toast.makeText(this, "Monitoring cannot be started for " + qrCode.getData(), Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }
    }
}
