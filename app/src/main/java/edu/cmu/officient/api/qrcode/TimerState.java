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

public abstract class TimerState {
    private ScannedQRCode scannedCode;
    private double startedAt=0, endAt=0;

    public TimerState(ScannedQRCode code) {
        scannedCode = code;
    }

    public void complete(){

    }

    public void status(){

    }

    public double timeout(){
        return endAt - startedAt;
    }

    public abstract void execute();

    public ScannedQRCode getScannedCode() {
        return scannedCode;
    }

    public void setScannedCode(ScannedQRCode scannedCode) {
        this.scannedCode = scannedCode;
    }

    public double getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(double startedAt) {
        this.startedAt = startedAt;
    }

    public double getEndAt() {
        return endAt;
    }

    public void setEndAt(double endAt) {
        this.endAt = endAt;
    }

    @NonNull
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(scannedCode);
        builder.append(" : (");
        builder.append(startedAt);
        builder.append(", ");
        builder.append(endAt);
        builder.append(")");
        return builder.toString();
    }
}
