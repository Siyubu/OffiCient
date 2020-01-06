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

package edu.cmu.officient.util;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class  Time implements Comparable<Time>, Serializable {
    private int hours=0, minutes=0, seconds=0;

    public Time(int hours, int minutes, int seconds) {
        // Use modulo to handle the case where user passes invalid hour
        this.hours = hours % 24;
        this.minutes = minutes % 60;
        this.seconds = seconds % 60;
    }

    public Time(int hours, int minutes) {
        this(hours, minutes, 0);
    }

    public Time(int hours) {
        this(hours, 0);
    }

    public static Time parse(String input) throws IllegalArgumentException {
        String [] parts = input.split(":");
        if (parts.length < 2)
            throw new IllegalArgumentException("The time format is not valid");
        int hours = Integer.parseInt(parts[0]), minutes = Integer.parseInt(parts[1]), seconds = 0;
        if (parts.length > 2)
            seconds = Integer.parseInt(parts[2]);
        return new Time(hours, minutes, seconds);
    }

    public static Time now(){
        Calendar calendar = Calendar.getInstance();
        return new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }

    @Override
    public int compareTo(Time time) {
        if (hours == time.hours) {
            if (minutes == time.minutes) {
                if (seconds == time.seconds)
                    return 0;
                return seconds - time.seconds;
            }
            return minutes - time.minutes;
        }
        return hours-time.hours;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
