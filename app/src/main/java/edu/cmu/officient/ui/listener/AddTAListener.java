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

import java.util.ArrayList;

import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktasks.GetCandidateTAList;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;
import edu.cmu.officient.ui.courses.AddTADialogFragment;

public class AddTAListener implements View.OnClickListener {
    private AppCompatActivity activity;
    private Course course;
    public AddTAListener(AppCompatActivity myActivity, Course course){
        activity = myActivity;
        this.course = course;
    }

    @Override
    public void onClick(View view) {
        //AddTADialogFragment fragment = new AddTADialogFragment(activity, course);
        //fragment.show(activity.getSupportFragmentManager(), "Pick a TA");
    }
}
