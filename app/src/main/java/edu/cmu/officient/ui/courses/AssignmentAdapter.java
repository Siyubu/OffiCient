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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {
    private List<Assignment> assignments;
    private AppCompatActivity parentActivity;

    public AssignmentAdapter(AppCompatActivity activity, List<Assignment> hwList){
        parentActivity = activity;
        assignments = hwList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Assignment assignment = assignments.get(position);
        holder.title.setText(assignment.getTitle());
        //holder.course.setText(assignment.getTerm().toString());
        holder.deadline.setText(parentActivity.getString(R.string.due_on, assignment.getDeadline()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the detail fragment

            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_assignment, parent, false);
        return new AssignmentAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, course, deadline;

        ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.assignment_block);
            title = view.findViewById(R.id.title);
            course = view.findViewById(R.id.course);
            deadline = view.findViewById(R.id.due_date);
        }
    }

}
