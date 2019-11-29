/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi, jwuyeh
 *  * Program : MSIT
 *  *
 *  * On our honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.model;

public class Assignment implements Scannable {
    private int assignmentId;
    private int courseId;
    private String title;
    private String published_on;
    private String deadline;
    private int expectedTime;
    private String availability;
    private boolean status;
    public Assignment() { // Build the object from the

    }
    public Assignment(int assignmentId, int courseId, String title, String published_on, String deadline, int expectedTime, String availability, boolean status){
        this.assignmentId = assignmentId;
        this.courseId = courseId;
        this.title = title;
        this.published_on = published_on;
        this.deadline = deadline;
        this.expectedTime = expectedTime;
        this.availability = availability;
        this.status = status;
    }
}
