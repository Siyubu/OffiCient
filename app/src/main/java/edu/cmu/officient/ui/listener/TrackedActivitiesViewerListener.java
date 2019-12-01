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

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.cmu.officient.ui.activities.OngoingActivitiesList;

public class TrackedActivitiesViewerListener  implements View.OnClickListener  {
    private Context context;

    public TrackedActivitiesViewerListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        // Do all the stuffs here. Basically opens the page with all the data
        Intent intent = new Intent(context, OngoingActivitiesList.class);
        context.startActivity(intent);
    }
}
