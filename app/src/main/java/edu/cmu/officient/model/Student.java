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

public class Student extends User {
    public Student(int id, String andrewId, String fullname, String altEmail, String phoneNumber) {
        super(id, andrewId, fullname, altEmail, phoneNumber, false);
    }
}
