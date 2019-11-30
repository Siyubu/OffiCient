/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : ECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.qr;

public class OfficeHour_QR_Builder extends QRBuilder {
    @Override
    QRGeneration setId(int officeHour_id) {
        qrgen.b_id=officeHour_id;

        return this.qrgen;
    }

    @Override
    QRGeneration setUser_id(int user_id) {
        qrgen.user_id=user_id;

        return  this.qrgen;
    }

    @Override
    QRGeneration setObjectType(String objT) {
        qrgen.object_type=objT;
        return this.qrgen;
    }

    @Override
    String  buildQR()
    {
        String txt= "OWNED_BY= "+this.qrgen.user_id+"\n" + "OBJECT_ID= "+this.qrgen.b_id + "\n"
                +"OBJECT_TYPE= "+this.qrgen.object_type;

        return txt;
    }
}
