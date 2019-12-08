/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : MSECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.courses;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;

public class DisplayCourseFragment extends Fragment {

    private Course course;
    private AppCompatActivity activity;
    private TextView view_course_detail;

    private ProgressBar progressBar;


    public DisplayCourseFragment(AppCompatActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        User user = ApplicationManager.getInstance(activity).getLoggedInUser();
        View view = inflater.inflate(R.layout.fragment_display_course, container, false);
        progressBar = view.findViewById(R.id.progress);
        view_course_detail = view.findViewById(R.id.course_detail);







        return view;
    }


    private class CourseDetail extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
          //  User user = ApplicationManager.getInstance().getLoggedInUser(activity);
            String[] attributes;
            if (args.length > 1)
                attributes = new String[]{"coursesList", "course_title","Course_term","course_students","Course_officeHours"};






            
            else
                attributes = new String[]{"coursesList"};
            RequestData requestData = new RequestData( activity,"http://gamfruits.com/officient_api/functions.php", attributes, args);
            jsonObject = requestData.getResponse();
            System.out.println(jsonObject);
            if(jsonObject!=null){
                try {
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    message = "error";
                    e.printStackTrace();
                }
            }
            else {
                message = "error";
            }
            return message;
        }

    }
}
