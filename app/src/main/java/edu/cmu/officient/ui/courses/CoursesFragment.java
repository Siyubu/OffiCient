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

package edu.cmu.officient.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.*;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;

public class CoursesFragment extends Fragment {

    private ProgressBar progressBar;
    private AppCompatActivity activity;
    private List<Course> courses = new ArrayList<>();
    private RecyclerView recyclerView;


    public CoursesFragment(){
        activity = (AppCompatActivity) getActivity();
    }

    public CoursesFragment (AppCompatActivity activity) {
        this.activity = activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courses, container, false);
        User user = ApplicationManager.getInstance(activity).getLoggedInUser();

        progressBar = root.findViewById(R.id.progress_bar);
        recyclerView = root.findViewById(R.id.courses_list);

        if (courses.size() == 0 ) {
            String [] args;
            if (user.isFaculty())
                args = new String[] {"coursesList", "" + user.getId()};
            else
                args = new String[] {"coursesList"};
            StandardRequestTask task = RequestTaskFactory.getTask(progressBar, root, activity, recyclerView, args);
            if (task != null)
                task.execute(args);
        }
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        return root;
    }

}