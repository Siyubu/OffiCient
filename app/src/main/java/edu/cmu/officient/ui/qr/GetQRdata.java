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
 private QRCode_Factory qrFact=new QRCode_Factory();

 public GetQRdata(){}




    public String qrData()
 {
     formatedData=qrFact.getQRCode("Assignment");

     return formatedData;
 }

    @Override
    public String toString() {
        return "GetQRdata{" +
                "formatedData='" + formatedData + '\'' +
                '}';
    }
}
