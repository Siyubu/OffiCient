package edu.cmu.officient.ui.courses;

        import android.os.Bundle;
        import android.view.Menu;

        import com.google.android.material.bottomappbar.BottomAppBar;

        import androidx.appcompat.app.AppCompatActivity;

        import edu.cmu.officient.R;

public class CoursesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);
        BottomAppBar appBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(appBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
