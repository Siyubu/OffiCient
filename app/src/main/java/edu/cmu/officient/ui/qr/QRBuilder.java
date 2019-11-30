/*
  ~  * @author Solange Iyubu
  ~  * AndrewID : siyubu
  ~  * Program : ECE
  ~  *
  ~  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
  ~  */
package edu.cmu.officient.ui.qr;

public abstract class QRBuilder {

    public QRGeneration qrgen;

    public QRBuilder()
    {
        qrgen=new QRGeneration();
    }

    abstract QRGeneration setId(int b_id);

    abstract QRGeneration setUser_id(int user_id);

    abstract QRGeneration setObjectType(String objT);


    public String  buildQR()
    {
        String txt= "OWNED_BY= "+this.qrgen.user_id+"\n" + "OBJECT_ID= "+this.qrgen.b_id + "\n"
                +"OBJECT_TYPE= "+this.qrgen.object_type;

        return txt;
    }

}
