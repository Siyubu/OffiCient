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

public class GetQRdata {
String formatedData;
 private QRCode_Factory qrFact;
 private QRBuilder builder;

 public GetQRdata(){
     builder=new Assignment_QR_Builder();

 }

    public String qrData()
 {

     builder.setObjectType("Assignment");
     builder.setUser_id(12);
     builder.setId(987);
     formatedData=builder.buildQR();
     return formatedData;
 }

    @Override
    public String toString() {
        return "GetQRdata{" +
                "formatedData='" + formatedData + '\'' +
                '}';
    }
}
