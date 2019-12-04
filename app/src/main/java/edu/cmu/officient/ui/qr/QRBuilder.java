/*
  ~  * @author Solange Iyubu
  ~  * AndrewID : siyubu
  ~  * Program : ECE
  ~  *
  ~  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
  ~  */
package edu.cmu.officient.ui.qr;

import java.sql.Date;

public abstract class QRBuilder {

    public QRGenerator qrgen;

    public QRBuilder()
    {
        qrgen=new QRGenerator();
    }



     QRGenerator setId(int assignment_id)
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

     QRGenerator setObjectName(String name)
    {
        qrgen.name=name;
        return this.qrgen;
    }

    QRGenerator setObjectDeadLine(Date deadLine)
    {
        qrgen.deadLine=deadLine;
        return this.qrgen;
    }
    QRGenerator setObjectAvailable_till(Date available_till)
    {
        qrgen.available_till=available_till;
        return this.qrgen;
    }
    QRGenerator setObjectPublished_date(Date published_date)
    {
        qrgen.published_date=published_date;
        return this.qrgen;
    }
    abstract String buildQR();


    
//    public String buildQR()
//    {
//        qrgen.txt = "OWNED_BY= "+this.qrgen.user_id+"\n" + "OBJECT_ID= "+this.qrgen.b_id + "\n"
//                +"OBJECT_TYPE= "+this.qrgen.object_type;
//
//        return this.qrgen.txt;
//    }




}
