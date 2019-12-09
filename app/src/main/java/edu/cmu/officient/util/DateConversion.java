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

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConversion {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public Date stringToDate(String dateString){
        //String dateString = "03/26/2012 11:49:00 AM";
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public String getStringDateTime (Date datetime, String separator) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy" + separator + "MM" + separator + "dd HH:mm:ss", Locale.getDefault());
        return format.format(datetime);
    }

    public String datePart(Date date){
        DateFormat df = new DateFormat();
        return (String) df.format("yyyy-MM-dd", date);

    }

    public Time stringToTime(String date) {
        String [] parts = date.split(" ");
        return Time.parse(parts[1]);
    }
}
