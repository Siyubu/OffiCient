/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID :siyubu
 *  * Program : ECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */
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

import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import edu.cmu.officient.exception.BuilderException;

public class OfficeHoursQRBuilder extends QRBuilder {
    @Override
    public QRGEncoder build() {
        BuilderException exception = QRBuilderExceptionFactory.getBuilderException(this);
        try {
            if (exception != null) {
                throw exception;
            }
        }
        catch (BuilderException e) {
            e.fix();
        }
        String text = String.format(Locale.getDefault(), "ID=%s\nTYPE=%s\nSTART_AT=%s\nEND_AT=%s\nDAY=%s\nOWNER_ID=%s\nANDREW_ID=%s\nCOURSE_ID=%s",
                getId(), getType(), getStartAt(), getEndAt(),getDayOfTheWeek(), getOwnerId(), getAndrewId(), getCourseId());
        return new QRGEncoder(text, null, QRGContents.Type.TEXT, 1000);
    }
}
