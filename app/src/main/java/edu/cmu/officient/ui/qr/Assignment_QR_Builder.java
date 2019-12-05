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

import java.sql.Date;

import androidmads.library.qrgenearator.QRGEncoder;
import edu.cmu.officient.api.qrcode.QRBuilder;

public class Assignment_QR_Builder extends QRBuilder {


    public Assignment_QR_Builder()
    {
        super();
    }

    QRGenerator setId(int assignment_id)
    {

        qrgen.b_id=assignment_id;
        return  this.qrgen;
    }

    @Override
    QRGenerator setUser_id(int user_id)
    {
        qrgen.user_id= user_id;
        return  this.qrgen;
    }

    @Override
    QRGenerator setObjectType(String objectT)
    {
        qrgen.object_type=objectT;
        return this.qrgen;
    }


        public String buildQR()
    {
        qrgen.txt = "OWNED_BY= "+this.qrgen.user_id+"\n" + "OBJECT_ID= "+this.qrgen.b_id + "\n"
                +"OBJECT_TYPE= "+this.qrgen.object_type;

        return this.qrgen.txt;
    }

    @Override
    public QRGEncoder build() {
        return null;
    }
}
