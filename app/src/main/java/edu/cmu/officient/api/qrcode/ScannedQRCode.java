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

import android.content.Context;

import androidx.annotation.Nullable;

import java.util.Date;

import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.model.User;

public class ScannedQRCode {
    private TimerState state;
    private User owner; // Represents the owner of the QR Code (Generally an instructor)
    private Scannable data;
    private String recordId = "";

    // Make it accessible from the package
    public final TimerState TIMER_INVALID = new TimerInvalid(this, ""), TIMER_STOPPING = new TimerStopping(this),
            TIMER_STARTING = new TimerStarting(this), TIMER_STARTED = new TimerStarted(this), TIMER_STOPPED = new TimerStopping(this);

    public ScannedQRCode(Scannable object){
        data = object;
        state = TimerStateFactory.getState(this);
    }

    public ScannedQRCode(Scannable object, User user) {
        this(object);
        owner = user;
        // When here, we need to check in which state we should put the object
    }

    public ScannedQRCode(Scannable object, User user, TimerState currentState) {
        data = object;
        owner = user;
        state = currentState;
    }

    public void run(Context context){
        state.execute(context);
    }

    public String status(){
        return state.status();
    }

    public void complete(){
        state.complete();
    }

    public double timeout(){
        return state.timeout();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ScannedQRCode /*&& owner !=null & data != null*/) {
            ScannedQRCode code = (ScannedQRCode) obj;
            return /*owner.equals(code.owner) &&*/ data.equals(code.data);
        }
        return false;
    }

    public TimerState getState() {
        return state;
    }

    public void setState(TimerState state) {
        this.state.setLeftAt(new Date());
        this.state = state;
        this.state.setEnteredAt(new Date());
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Scannable getData() {
        return data;
    }

    public void setData(Scannable data) {
        this.data = data;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
