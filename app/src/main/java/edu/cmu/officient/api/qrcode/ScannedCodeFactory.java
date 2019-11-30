/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.api.qrcode;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import edu.cmu.officient.model.*;
import edu.cmu.officient.wservices.*;

public class ScannedCodeFactory {
    public static ScannedQRCode getCodeObject(ObjectType type, int id, int userId) {
        OfficientStorage storage = new PermanentStorage();
        User user = storage.getUser(userId);
        Scannable data = null;
        switch (type) {
            case ASSIGNMENT:
                data = storage.getAssignment(id);
                break;
            case COURSE:
                data = storage.getCourse(id);
        }
        return new ScannedQRCode(data, user);
    }

    public static ScannedQRCode loadCode(String output) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(output));
        }
        catch (IOException e) {/* */}
        int userId = Integer.parseInt(properties.getProperty("OWNED_BY"));
        int id = Integer.parseInt(properties.getProperty("OBJECT_ID"));
        ObjectType type = ObjectType.fromString(properties.getProperty("OBJECT_TYPE"));
        OfficientStorage storage = new PermanentStorage();
        User user = storage.getUser(userId);
        Scannable data = null;
        switch (type) {
            case ASSIGNMENT:
                data = storage.getAssignment(id);
                break;
            case COURSE:
                data = storage.getCourse(id);
        }
        return new ScannedQRCode(data, user);
    }
}
