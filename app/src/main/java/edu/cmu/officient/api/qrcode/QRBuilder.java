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
  ~  * @author Solange Iyubu
  ~  * AndrewID : siyubu
  ~  * Program : ECE
  ~  *
  ~  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
  ~  */
package edu.cmu.officient.api.qrcode;

import java.util.Date;

import androidmads.library.qrgenearator.QRGEncoder;
import edu.cmu.officient.util.Time;

public abstract class QRBuilder {
    private Integer ownerId=0, dayOfTheWeek=-1;
    private ObjectType type;
    private String name, id;
    private Date deadline=null, availableTill=null, published_time=null;
    Time startAt, endAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(Date availableTill) {
        this.availableTill = availableTill;
    }

    public Date getPublished_time() {
        return published_time;
    }

    public void setPublished_time(Date published_time) {
        this.published_time = published_time;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public Time getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    public Integer getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(Integer dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public abstract QRGEncoder build();
}
