/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.storage;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.Instructor;
import edu.cmu.officient.model.OfficeHours;
import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.model.Student;
import edu.cmu.officient.model.Term;
import edu.cmu.officient.util.DateConversion;

public class RemoteStorage extends OfficientStorage implements ReadData, UpdateData, DeleteData, AddData{
    Context context;
    public RemoteStorage(Context context){
        this.context = context;
    }
    public Assignment getAssignment(int id) { // Builds an object using the ID
        GetAssignment ga = new GetAssignment();
        ga.execute("assignment_details", id+"");
        System.out.println(ga.getStatus());
        return ga.getAssignment();
        //return new Assignment(id, 3, DATE_FORMATTER.parse("08/11/2019 10:00:00"), DATE_FORMATTER.parse("01/12/2019 11:59:00"), DATE_FORMATTER.parse("08/12/2019 13:00:00"), "Location Aware", getCourse(1));
        //return null;
    }

    public Course getCourse(int id) {
        Term term;
        try {
            term = new Term("Fall 2019", DATE_FORMATTER.parse("08/01/2019 10:00:00"), DATE_FORMATTER.parse("08/09/2019 11:59:00"));
        }
        catch (ParseException e) {
            term = new Term();
        }
        List<Instructor> instructors = new ArrayList<>();
        return new Course(1, "Design Patterns for SD", "18785", term, instructors, new ArrayList<Student>(), new ArrayList<OfficeHours>());
    }
    @Override
    public void updateTaskRecord(long id, Date endDate, Scannable scannable) {

    }

    @Override
    public long addTaskRecord(Scannable scannable, Date date) {
        return 0;
    }

    private class GetAssignment extends AsyncTask<String, String, String> {
        private Assignment assignment;
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private Assignment getAssignment(){
            return assignment;
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
            String[] attributes = new String[]{"assignment_details", "assignment_id"};
            RequestData requestData = new RequestData( context,"http://gamfruits.com/officient_api/functions.php", attributes, args);
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

        protected void onPostExecute(String result){
            System.out.println(result);
            if (result.equalsIgnoreCase("success")){
                try {
                    final JSONObject data = jsonObject.getJSONObject("data");
                    assignment = new Assignment(data.getInt("id"), Integer.parseInt(data.getString("expected_time")),
                            DATE_FORMATTER.parse(data.getString("published_on")),
                            DATE_FORMATTER.parse(data.getString("deadline")),
                            DATE_FORMATTER.parse(data.getString("availability")),
                            data.getString("title"), getCourse(Integer.parseInt(data.getString("course_id"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, "Assignment Retrieved", Toast.LENGTH_SHORT).show();
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the internet.", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("no_data")){
                //items.add("Term list empty");
                Toast.makeText(context, "Assignment not found", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
