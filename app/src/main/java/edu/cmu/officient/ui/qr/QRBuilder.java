/*
  ~  * @author Solange Iyubu
  ~  * AndrewID : siyubu
  ~  * Program : ECE
  ~  *
  ~  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
  ~  */
package edu.cmu.officient.ui.qr;

public class QRBuilder {

    private QRGeneration qrgen;

    public QRBuilder()
    {
        qrgen=new QRGeneration();
    }

    public QRGeneration setId(int b_id)
    {
        qrgen.b_id=b_id;

        return this.qrgen;
    }

    public QRGeneration setUser_id(int user_id)
    {
        qrgen.user_id=user_id;

        return  this.qrgen;
    }
    public QRGeneration setObjectType(String objT)
    {
        qrgen.object_type=objT;
        return this.qrgen;
    }

    public String  build()
    {
        String txt= "OWNED_BY: "+this.qrgen.user_id+"\n" + "OBJECT_ID: "+this.qrgen.b_id + "\n"
                +"OBJECT_TYPE: "+this.qrgen.object_type;

        return txt;
    }

}
