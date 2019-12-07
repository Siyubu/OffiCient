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

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.cmu.officient.ui.assignments.Add_assignment;
import edu.cmu.officient.ui.qr.QRGenerator;

public class AddAssignmentListener implements View.OnClickListener
{
    private Context context;

    public AddAssignmentListener(Context context)
    {
        this.context=context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, Add_assignment.class);
        context.startActivity(intent);

    }
}
