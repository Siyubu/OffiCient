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
 private int id_from_database;
 private int user_id_from_database;
 private String object_type;
 private String fomatedData;
 private QRBuilder qrBuilder;

 public GetQRdata(){}

    public GetQRdata(int id_from_database, int user_id_from_database,String object_type) {

        this.id_from_database = id_from_database;
        this.user_id_from_database = user_id_from_database;
        this.object_type=object_type;

    }

    public int getId_from_database() {
        return id_from_database;
    }

    public void setId_from_database(int id_from_database) {
        this.id_from_database = id_from_database;
    }

    public int getUser_id_from_database() {
        return user_id_from_database;
    }

    public void setUser_id_from_database(int user_id_from_database) {
        this.user_id_from_database = user_id_from_database;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String qrData()
 {
     id_from_database=123;
     user_id_from_database=12356;

     qrBuilder=new QRBuilder();
     qrBuilder.setId(id_from_database);
     qrBuilder.setUser_id(user_id_from_database);
     fomatedData=qrBuilder.build();

     return fomatedData;
 }

    @Override
    public String toString() {
        return "GetQRdata{" +
                "id_from_database=" + id_from_database +
                ", user_id_from_database=" + user_id_from_database +
                '}';
    }
}
