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

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import edu.cmu.officient.R;
import edu.cmu.officient.ui.assignments.AddAssignmentFragment;


public class AddAssignmentListener implements View.OnClickListener
{
    private AppCompatActivity activity;

    public AddAssignmentListener(AppCompatActivity activity)
    {
        this.activity=activity;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new AddAssignmentFragment(activity)).commit();
        transaction.addToBackStack(null);

    }
}
