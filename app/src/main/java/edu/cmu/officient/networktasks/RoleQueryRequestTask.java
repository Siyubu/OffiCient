/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.networktasks;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import edu.cmu.officient.R;

public class RoleQueryRequestTask extends StandardRequestTask {
    private final int [] taHidden = {R.id.enroll_in}, taVisible = {R.id.add_assignment}, studentHidden = {R.id.enroll_in, R.id.add_assignment_btn, R.id.add_office_hours_btn},
            studentVisible = {R.id.view_statistics}, failedHidden = {R.id.add_assignment_btn, R.id.add_office_hours_btn, R.id.add_assignment};

    public RoleQueryRequestTask(AppCompatActivity activity, View root, View progressMarker) {
        super(activity, root, progressMarker);
    }

    @Override
    protected String getOutput() {
        System.out.println(getData());
        try {
            return getData().getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    protected void processData(String result) {
        if (result.equalsIgnoreCase("success")){
            String role;
            try {
                role = getData().getString("data");
            } catch (JSONException e) {
                e.printStackTrace();
                role = "Student";
            }
            if (role.equals("Student")) {
                // We agreed on the input fields
                for(int id : studentVisible) {
                    getRoot().findViewById(id).setVisibility(View.VISIBLE);
                }
                for (int id : studentHidden)
                    getRoot().findViewById(id).setVisibility(View.GONE);
            }
            else if (role.equals("TA")){ // Should be TA then
                for(int id : taVisible) {
                    getRoot().findViewById(id).setVisibility(View.VISIBLE);
                }
                for (int id : taHidden)
                    getRoot().findViewById(id).setVisibility(View.GONE);
            }
        }
        else if (result.equalsIgnoreCase("failed")) {
            for (int id : failedHidden)
                getRoot().findViewById(id).setVisibility(View.GONE);
        }
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[] {"userRole", "andrewId", "course_id"};
    }
}
