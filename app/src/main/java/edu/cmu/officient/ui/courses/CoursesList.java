package edu.cmu.officient.ui.courses;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.cmu.officient.R;
import edu.cmu.officient.ui.listener.QRCodeScannerListener;

public class CoursesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);
        BottomAppBar appBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(appBar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new QRCodeScannerListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
