package edu.cmu.officient.ui.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.*;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.ui.listener.*;
import edu.cmu.officient.ui.qr.QRCodeScanner;
import edu.cmu.officient.ui.users.LoginActivity;
import edu.cmu.officient.ui.util.NavigationInitializer;

public class CoursesList extends AppCompatActivity {
    private ApplicationManager manager = ApplicationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (manager.getLoggedInUser(this) == null) {
            // If user not logged in, we have them go login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavigationInitializer.setUpBottomNavigationBar(this, navView);

        FloatingActionButton scanCode = findViewById(R.id.scan_code), viewStatus = findViewById(R.id.see_activities);
        scanCode.setOnClickListener(new QRCodeScannerListener(this));
        viewStatus.setOnClickListener(new TrackedActivitiesViewerListener(this));
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
            Toast.makeText(this, R.string.invalid_qr_code, Toast.LENGTH_SHORT).show();
        }
    }
}
