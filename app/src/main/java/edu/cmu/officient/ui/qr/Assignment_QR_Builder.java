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

public class Assignment_QR_Builder extends QRBuilder {


    public Assignment_QR_Builder() {
        super();
    }


    @Override
    QRGeneration setId(int assignment_id) // here I need a querry of Assignment ID
    {

        qrgen.user_id=assignment_id;

        return  this.qrgen;
    }

    @Override
    QRGeneration setUser_id(int user_id) {
        qrgen.user_id=user_id;
        return  this.qrgen;
    }

    @Override
    QRGeneration setObjectType(String objectT) {
        qrgen.object_type=objectT;
        return this.qrgen;
    }
}
