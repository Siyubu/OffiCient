/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.listener;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import edu.cmu.officient.R;
import edu.cmu.officient.ui.courses.AddCourseActivity;

public class AddCourseListener implements View.OnClickListener {
    private AppCompatActivity activity;

    public AddCourseListener(AppCompatActivity activity/*, Context context*/) {
        this.activity = activity;
    }
    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new AddCourseActivity.AddCourse()).commit();
        transaction.addToBackStack(null);
        /*Intent intent = new Intent(activity, AddCourseActivity.class);
        activity.startActivity(intent);*/
    }
}
