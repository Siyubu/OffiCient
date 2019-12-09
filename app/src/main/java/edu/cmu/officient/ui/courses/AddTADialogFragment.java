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

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;

public class AddTADialogFragment extends DialogFragment {
    private String [] candidates;
    private int courseId;

    public AddTADialogFragment(String[] data, int course) {
        candidates = data;
        this.courseId = course;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View root = inflater.inflate(R.layout.fragment_add_ta, null);
        // Configure the EditText here
        final AutoCompleteTextView students = root.findViewById(R.id.search);
        //Spinner students = root.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, candidates);
        students.setAdapter(adapter);
        /*String [] */

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.select_ta)
                .setView(root)
                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String andrewId = students.getText().toString();
                        List<String> list = Arrays.asList(candidates);
                        if (!list.contains(andrewId)) {
                            Toast.makeText(getActivity(), "The user " + andrewId + " cannot be a TA for the class.", Toast.LENGTH_SHORT).show();
                        }
                        else { // Send the request to the server
                            StandardRequestTask task = RequestTaskFactory.getTask(null, root, null, null, "enrollTA");
                            if (task != null)
                                task.execute("enrollTA", "" + courseId, andrewId);
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing
                    }
                });


        return builder.create();
    }

}
