/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User {
    private final String EMAIL_PREFIX = "andrew.cmu.edu";
    private int id;
    private String andrewId, fullname, altEmail, phoneNumber;
    private boolean isFaculty;

    public User(){

    }

    public User(int id, String andrewId, String fullname, String altEmail, String phoneNumber) {
        this.id = id;
        this.andrewId = andrewId;
        this.fullname = fullname;
        this.altEmail = altEmail;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String andrewId, String fullname, String altEmail, String phoneNumber, boolean isFaculty) {
        this(id, andrewId, fullname, altEmail, phoneNumber);
        this.isFaculty = isFaculty;
    }

    public boolean isFaculty(){
        return isFaculty;
    }

    public void setFaculty(boolean faculty) {
        isFaculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAndrewId() {
        return andrewId;
    }

    public void setAndrewId(String andrewId) {
        this.andrewId = andrewId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return andrewId + "@" + EMAIL_PREFIX;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof User) {
            return andrewId.equals(((User) obj).andrewId);
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        if (fullname == null || fullname.equals(""))
            return andrewId;
        return fullname;
    }
}
