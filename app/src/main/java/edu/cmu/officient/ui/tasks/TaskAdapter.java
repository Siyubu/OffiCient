/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.tasks;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.logic.ApplicationManager;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<ScannedQRCode> codes;
    private Context context;

    public TaskAdapter(Context context, List<ScannedQRCode> codes) {
        this.context = context;
        this.codes = codes;
    }

    public void add(int position, ScannedQRCode code) {
        codes.add(position, code);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        ApplicationManager.getInstance(context).processScannedCode(codes.get(position)); // After here, for sure the element is gone
        codes.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ScannedQRCode code = codes.get(position);
        switch (code.getData().getType()) {
            case "Assignment":
                holder.taskType.setImageResource(R.drawable.assignment_icon);
                break;
            case "Office Hours":
                holder.taskType.setImageResource(R.drawable.office_hours_icon);
                break;
        }
        holder.taskTitle.setText(code.getData().toString());
        holder.taskDate.setText(context.getString(R.string.started_at, DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(code.getState().getEnteredAt())));

        holder.stopTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return codes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle, taskDate;
        ImageView taskType;
        Button stopTask;
        ViewHolder(View v){
            super(v);
            taskType = v.findViewById(R.id.task_object_type);
            taskTitle = v.findViewById(R.id.task_object_title);
            taskDate = v.findViewById(R.id.task_started_time);
            stopTask = v.findViewById(R.id.manual_task_stop);
        }
    }
}
