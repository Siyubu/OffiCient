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
import edu.cmu.officient.api.qrcode.ObjectType;

public abstract class QRBuilder {
    private Integer id=0, ownerId=0;
    private ObjectType type;
    private String name;
    private Date deadline=null, availableTill=null, published_time=null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public abstract QRGEncoder build();
}
