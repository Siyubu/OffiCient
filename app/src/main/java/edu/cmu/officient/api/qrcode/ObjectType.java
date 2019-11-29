/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.api.qrcode;

public enum ObjectType {
    ASSIGNMENT("Assignment"),
    OFFICE_HOURS("Office Hours"),
    COURSE("Course"),
    UNDEFINED("Undefined");

    private String description;

    ObjectType(String name) {
        description = name;
    }

    public ObjectType fromString(String description) {
        if (description.equalsIgnoreCase("Assignment"))
            return ASSIGNMENT;
        else if (description.equalsIgnoreCase("Office Hours") ||
                description.equalsIgnoreCase("Office_Hours"))
            return OFFICE_HOURS;
        else if (description.equalsIgnoreCase("Course"))
            return COURSE;
        else
            return UNDEFINED;
    }

    public String toString(){
        return description;
    }
}
