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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public abstract class TimerState {
    private ScannedQRCode scannedCode;
    private Date enteredAt, leftAt;

    public TimerState(ScannedQRCode code) {
        scannedCode = code;
    }

    public void complete(){
        // Any db action should be done here
    }

    public String status(){
        return getClass().getSimpleName();
    }

    public double timeout(){
        if (leftAt != null)
            return leftAt.getTime() - enteredAt.getTime();
        return new Date().getTime() - enteredAt.getTime();
    }

    public abstract void execute();

    public ScannedQRCode getScannedCode() {
        return scannedCode;
    }

    public void setScannedCode(ScannedQRCode scannedCode) {
        this.scannedCode = scannedCode;
    }

    public Date getEnteredAt() {
        return enteredAt;
    }

    public void setEnteredAt(Date startedAt) {
        this.enteredAt = startedAt;
    }

    public Date getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(Date endAt) {
        this.leftAt = endAt;
    }

    @NonNull
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(scannedCode);
        builder.append(" : (");
        builder.append(enteredAt);
        builder.append(", ");
        builder.append(leftAt);
        builder.append(")");
        return builder.toString();
    }
}
