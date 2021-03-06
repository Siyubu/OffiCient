/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : ECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.listener;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import edu.cmu.officient.R;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.ui.assignments.AddAssignmentActivity;
import edu.cmu.officient.ui.assignments.AddAssignmentFragment;


public class AddAssignmentListener implements View.OnClickListener
{
    private AppCompatActivity activity;
    private Course course;

    public AddAssignmentListener(AppCompatActivity activity,Course course)
    {
        this.activity=activity;
        this.course=course;
    }

    @Override
    public void onClick(View v) {
        /*FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new AddAssignmentFragment(activity,course)).commit();
        transaction.addToBackStack(null);*/

        Intent intent = new Intent(activity, AddAssignmentActivity.class);
        intent.putExtra("course", course);
        activity.startActivity(intent);

    }
}
