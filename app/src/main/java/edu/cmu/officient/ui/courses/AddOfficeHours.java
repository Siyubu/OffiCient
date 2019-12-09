/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.courses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.model.OfficeHours;
import edu.cmu.officient.ui.assignments.AddAssignmentFragment;

public class AddOfficeHours extends Fragment {
    private AppCompatActivity activity;
    OfficeHours selectedoh;
    private ProgressBar progressBar;

    public AddOfficeHours(AppCompatActivity activity, OfficeHours officeHours)
    {
        this.activity=activity;
        this.selectedoh=officeHours;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_assigment, container, false);
       progressBar =view.findViewById(R.id.load);
//        addAssignmentBtn=view.findViewById(R.id.add_course);
//        assign_title=view.findViewById(R.id.ass_title);
//        expect_time=view.findViewById(R.id.exp_time);
//        deadline=view.findViewById(R.id.due_date);
//        availability=view.findViewById(R.id.avail_time);
//
//        addAssignmentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                new AddAssignmentFragment.AddAssignment().execute("addAssignment",assign_title.getText().toString(),deadline.getText().toString(),new Date().toString(),
//                        availability.getText().toString(),expect_time.getText().toString(), selectedCourse.getId() + "");
//                Toast.makeText(activity, availability.getText()+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(activity, new Date().toString()+"", Toast.LENGTH_SHORT).show();
//            }
//        });
//        deadline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                calendar_time(deadline);
//
//            }
//        });
//        availability.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                calendar_time(availability);
//
//            }
//        });

        return view;
    }

    private class AddOh extends AsyncTask<String, String, String>
    {
        private JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args)
        {
            String message=" ";
            String[] attributes = new String[]{"addOfficeHours", "title", "deadline", "published_on", "availability","expected_time","course_id"};
            RequestData requestData = new RequestData(activity, "http://gamfruits.com/officient_api/functions.php", attributes, args);
            jsonObject = requestData.getResponse();
            System.out.println(jsonObject);
            try {
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                message = "error";
            }
            return message;
        }
        @Override
        protected void onPostExecute(String result)
        {
            progressBar.setVisibility(View.GONE);
            if (result.equalsIgnoreCase("success")){
                Toast.makeText(activity, "Assignment added Successfully", Toast.LENGTH_LONG).show();
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, "Unable to connect to the internet. Assignment not added", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                Toast.makeText(activity, "Problem with App. Contact admin.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
