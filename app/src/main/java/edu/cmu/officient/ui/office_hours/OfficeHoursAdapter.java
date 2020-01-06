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


package edu.cmu.officient.ui.office_hours;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;

import edu.cmu.officient.R;
import edu.cmu.officient.model.OfficeHours;

public class OfficeHoursAdapter extends RecyclerView.Adapter<OfficeHoursAdapter.ViewHolder> {
    private List<OfficeHours> officeHours;
    private AppCompatActivity parentActivity;

    public OfficeHoursAdapter(AppCompatActivity activity, List<OfficeHours> ohList){
        parentActivity = activity;
        officeHours = ohList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final OfficeHours oh = officeHours.get(position);
        holder.title.setText(oh.getOwnerId());
        holder.day.setText(parentActivity.getString(R.string.oh_day_hours,
                DateFormatSymbols.getInstance(Locale.getDefault()).getWeekdays()[oh.getDay()], oh.getStartAt(), oh.getEndAt()));
        holder.venue.setText(oh.getVenue());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, OfficeHourDetailActivity.class);
                intent.putExtra("office_hour", officeHours.get(position));
                parentActivity.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_office_hours, parent, false);
        return new OfficeHoursAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return officeHours.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, day, venue;

        ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.oh_block);
            title = view.findViewById(R.id.title);
            day = view.findViewById(R.id.timeframe);
            venue = view.findViewById(R.id.venue);
        }
    }

}
