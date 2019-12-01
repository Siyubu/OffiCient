package edu.cmu.officient.ui.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.ScannedCodeFactory;
import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.ui.listener.QRCodeGeneratorListener;
import edu.cmu.officient.ui.listener.QRCodeScannerListener;
import edu.cmu.officient.ui.listener.TrackedActivitiesViewerListener;
import edu.cmu.officient.ui.qr.QRCodeScanner;
import edu.cmu.officient.ui.util.NavigationInitializer;
import edu.cmu.officient.wservices.OfficientStorage;
import edu.cmu.officient.wservices.StaticStorage;

public class CoursesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);
        /*BottomAppBar appBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(appBar);*/

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavigationInitializer.setUpBottomNavigationBar(this, navView);

        FloatingActionButton scanCode = findViewById(R.id.scan_code), viewStatus = findViewById(R.id.see_activities);
        scanCode.setOnClickListener(new QRCodeScannerListener(this));
        viewStatus.setOnClickListener(new TrackedActivitiesViewerListener(this));



        //Button loginBtn=findViewById(R.id.login);
        //loginBtn.setOnClickListener(new QRCodeGeneratorListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QRCodeScanner.QR_CODE_READER && data != null) {
            String output = data.getStringExtra("code");
            ScannedQRCode qrCode = ScannedCodeFactory.loadCode(output);
            if (qrCode != null) {
                OfficientStorage storage = new StaticStorage();
                switch (storage.processScannedCode(qrCode)) {
                    case OfficientStorage.STATE_STARTED:
                        Toast.makeText(this, "Monitoring has started for " + qrCode.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case OfficientStorage.STATE_STOPPED:
                        Toast.makeText(this, "Monitoring has stopped for " + qrCode.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case OfficientStorage.STATE_EXPIRED:
                        Toast.makeText(this, "Monitoring cannot be started for " + qrCode.getData(), Toast.LENGTH_SHORT).show();
                        break;

                }
            }

        }
    }

}
