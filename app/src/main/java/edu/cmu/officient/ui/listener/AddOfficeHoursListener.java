/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

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

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.cmu.officient.model.Course;
import edu.cmu.officient.ui.office_hours.AddOfficeHourActivity;

public class AddOfficeHoursListener implements View.OnClickListener {

    private AppCompatActivity activity;
    private Course course;

    public AddOfficeHoursListener(AppCompatActivity activity,Course course)
    {
        this.activity=activity;
        this.course=course;
    }

    @Override
    public void onClick(View v) {
        /*FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new AddOfficeHours(activity,course)).commit();
        transaction.addToBackStack(null);
*/
        Intent intent = new Intent(activity, AddOfficeHourActivity.class);
        intent.putExtra("course", course);
        activity.startActivity(intent);
    }
}
