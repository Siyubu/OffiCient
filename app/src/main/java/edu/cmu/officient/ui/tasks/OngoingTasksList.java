/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;

public class OngoingTasksList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_tasks_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AdvancedRecyclerView recyclerView = findViewById(R.id.tasks_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<ScannedQRCode> scannedCodes = ApplicationManager.getInstance(this).getOngoingActivities();
        TaskAdapter adapter = new TaskAdapter(this, scannedCodes);
        recyclerView.setEmptyView(findViewById(R.id.no_tasks));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // Temporary solution to go back to the previous activity
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
