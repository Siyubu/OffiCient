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
 private QRBuilder builder;

 public GetQRdata(){
     builder=new Assignment_QR_Builder();

 }

    public void getAssignmantData()
 {
     builder.setObjectType("Assignment");
     builder.setUser_id(12);
     builder.setId(987);
     builder.buildQR();
 }

    public String getOfficeHourData()
    {
        String officeHourData=" ";
//        builder.setObjectType("DPSD");
//        builder.setUser_id(12);
//        builder.setId(987);
//        assignmentData=builder.buildQR();
        return officeHourData;
    }

    @Override
    public String toString() {
        return "GetQRdata{" +
                "builder=" + builder +
                '}';
    }
}
