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
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RequestTaskFactory {
    public static StandardRequestTask getTask(View progress, View root, AppCompatActivity activity, View recycler, String ... args) {
        switch (args[0]) {
            case "userRole":
                return new RoleQueryRequestTask(activity, root, progress);
            case "coursesList":
                return new CoursesListQueryTask(activity, root, progress, args.length, (RecyclerView) recycler);
            case "login":
                return new LogUserInTask(activity, root, progress);
            case "signup":
                return new RegisterUserTask(activity, root, progress);
            case "update_profile":
                return new UpdateProfileTask(activity, root, progress, args);
            case "enrollStudent":
                return new EnrollUserTask(activity, root, progress);
            case "allStudents": // Assuming we are already in the Fragment
                return new GetCandidateTAList(activity, root, progress, Integer.parseInt(args[1]));
            case "enrollTA":
                return new AddTATask(activity, root, progress);
            case "addAssignment":
                return new AddAssignmentTask(activity, root, progress);
            case "addOfficeHours":
                return new AddAssignmentTask(activity, root, progress);
        }
        return null;
    }
}
