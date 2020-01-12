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

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class User implements Serializable {
    public static final String EMAIL_SUFFIX = "@andrew.cmu.edu";
    private int id;
    private String andrew_id, fullname, alternative_email, phone_number;
    private boolean faculty;

    public User(){

    }

    public User(int id, String andrew_id, String fullname, String alternative_email, String phone_number) {
        this.id = id;
        this.andrew_id = andrew_id;
        this.fullname = fullname;
        this.alternative_email = alternative_email;
        this.phone_number = phone_number;
    }

    public User(int id, String andrew_id, String fullname, String alternative_email, String phone_number, boolean faculty) {
        this(id, andrew_id, fullname, alternative_email, phone_number);
        this.faculty = faculty;
    }

    public boolean isFaculty(){
        return faculty;
    }

    public void setFaculty(boolean faculty) {
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PropertyName("andrew_id")
    public String getAndrewId() {
        return andrew_id;
    }

    @PropertyName("andrew_id")
    public void setAndrewId(String andrew_id) {
        this.andrew_id = andrew_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return andrew_id + "@" + EMAIL_SUFFIX;
    }

    @PropertyName("alternative_email")
    public String getAlternativeEmail() {
        return alternative_email;
    }

    @PropertyName("alternative_email")
    public void setAlternativeEmail(String alternative_email) {
        this.alternative_email = alternative_email;
    }

    @PropertyName("phone_number")
    public String getPhoneNumber() {
        return phone_number;
    }

    @PropertyName("phone_number")
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof User) {
            return andrew_id.equals(((User) obj).andrew_id);
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        if (fullname == null || fullname.equals(""))
            return andrew_id;
        return fullname;
    }
}
