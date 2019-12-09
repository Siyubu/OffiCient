/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.cmu.officient.model.*;

public class ModelObjectBuilder {
    public static Course buildCourse(JSONObject object) {
        try {
            // Get course details first
            JSONObject courseDetails = object.getJSONObject("course_detail"), termDetails = object.getJSONObject("term");
            int id = courseDetails.getInt("course_id");
            String title = courseDetails.getString("title"), code = courseDetails.getString("code");
            DateConversion converter = new DateConversion();
            Term term = new Term(termDetails.getInt("id"), termDetails.getString("name"), converter.stringToDate(termDetails.getString("start_date")),
                    converter.stringToDate(termDetails.getString("end_date")));
            List<OfficeHours> officeHours = new ArrayList<>();

            if (object.has("ohrs")) {
                JSONArray ohs = object.getJSONArray("ohrs");
                Date date;
                for (int i=0; i< ohs.length(); ++i) {
                    JSONObject data = (JSONObject) ohs.get(i);
                    date = converter.stringToDate(data.getString("scheduled_start_time"));
                    Calendar calendar = Calendar.getInstance(Locale.getDefault());
                    calendar.setTime(date);
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    Instructor user = new Instructor(data.getInt("user_id"), data.getString("andrewId"), data.getString("name"), data.getString("alternative_email"),
                            data.getString("phoneNumber"));

                    officeHours.add(new OfficeHours(data.getInt("id"), day, data.getString("venue"), data.getString("description"),
                            converter.stringToTime((data.getString("scheduled_start_time"))), converter.stringToTime(data.getString("scheduled_end_time")), null, user));

                }
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            List<Assignment> assignments = new ArrayList<>();
            // Assignments
            if (object.has("assignments")) {
                JSONArray array = object.getJSONArray("assignments");
                Log.e("TEST", array.toString());
                for (int i=0; i<array.length(); ++i) {
                    JSONObject data = (JSONObject) array.get(i);
                    try {
                        Log.e("TEST", (formatter.parse(data.getString("deadline"))).toString());
                        assignments.add(new Assignment(data.getInt("id"), (int) data.getDouble("expected_time"), formatter.parse(data.getString("published_on")),
                                formatter.parse(data.getString("deadline")), formatter.parse(data.getString("availability")), data.getString("title"), null));
                    }
                    catch (ParseException e) {e.printStackTrace();}
                }

            }
            Course course = new Course(id, title, code, term, assignments, officeHours);
            for (Assignment assignment : assignments) {
                assignment.setCourse(course);
            }
            return course;

        }
        catch (JSONException e) {
            /* */
            e.printStackTrace();
        }
        return null;
    }

    public static User buildUser(JSONObject object) {
        try {
            int id = object.getInt("id");
            String andrewId = object.getString("andrewId"), name = object.getString("name"), altEmail = object.getString("alternative_email"),
                    phoneNumber = object.getString("phoneNumber");
            return new User(id, andrewId, name, altEmail, phoneNumber);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
