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


    public Assignment_QR_Builder()
    {
        super();
    }

    QRGenerator setId(int assignment_id) // here I need a querry of Assignment ID
    {

        qrgen.b_id=assignment_id;
        return  this.qrgen;
    }


    QRGenerator setUser_id(int user_id)
    {
        qrgen.user_id= user_id;
        return  this.qrgen;
    }


    QRGenerator setObjectType(String objectT)
    {
        qrgen.object_type=objectT;
        return this.qrgen;
    }

    @Override
public QRGenerator buildQR()
{
    qrgen.txt = "OWNED_BY= "+this.qrgen.user_id+"\n" + "OBJECT_ID= "+this.qrgen.b_id + "\n"
            +"OBJECT_TYPE= "+this.qrgen.object_type;

    return this.qrgen;
}

}
