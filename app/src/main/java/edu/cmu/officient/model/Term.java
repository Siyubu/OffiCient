/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi, Wuyeh Jobe
 *  * AndrewID : vadjibi, jwuyeh
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.model;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

import edu.cmu.officient.util.DateConversion;

public class Term {
    private int id;
    private String name;
    private Date startDate, endDate;

    public Term(){
        this("", new Date(), new Date());
    }
    public Term(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Term(int id, String name, Date startDate, Date endDate){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Term) {
            Term o = (Term) obj;
            return name.equals(o.getName()) && startDate.equals(o.getStartDate()) && endDate.equals(o.getEndDate());
        }
        return false;
    }

    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
       return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
       return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        DateConversion dateConversion = new DateConversion();
        Calendar cal = Calendar.getInstance();
        System.out.println(endDate);
        cal.setTime(endDate);
        return name + " " + cal.get(Calendar.YEAR);
    }
}
