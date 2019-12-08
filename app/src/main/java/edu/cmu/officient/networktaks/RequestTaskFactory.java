/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.networktaks;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RequestTaskFactory {
    public static StandardRequestTask getTask(View progress, View root, AppCompatActivity activity, RecyclerView recycler, String ... args) {
        switch (args[0]) {
            case "userRole":
                return new RoleQueryRequestTask(activity, root, progress);
            case "coursesList":
                return new CoursesListQueryTask(activity, root, progress, args.length, recycler);
            case "login":
                return new LogUserInTask(activity, root, progress);
            case "signup":
                return new RegisterUserTask(activity, root, progress);
            case "update_profile":
                return new UpdateProfileTask(activity, root, progress, args);
        }
        return null;
    }
}
