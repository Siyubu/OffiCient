/*
 *
 *  * @author Wuyeh Jobe
 *  * AndrewID : jwuyeh
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.DBCommunication;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestData {

    private String apiLink;
    private String[] attributes;
    private String[] arguments;
    private Context context;
    public RequestData(Context context, String apiLink, String[] attributes, String[] arguments){
        this.context = context;
        this.apiLink = apiLink;
        this.attributes = attributes;
        this.arguments = arguments;
    }

    public JSONObject getResponse(){
        JSONObject jsonObject;
        CheckInternetConnection checkInternetConnection = CheckInternetConnection.getInstance();
        if(checkInternetConnection.isAvailable()){
            JSONProtocol httpJsonParser = new JSONProtocol();
            Map<String, String> httpParams = new HashMap<>();
            for (int i=0;i<attributes.length;i++){
                httpParams.put(attributes[i], arguments[i]);
            }
            jsonObject = httpJsonParser.makeHttpRequest(
                    "http://gamfruits.com/officient_api/functions.php", "POST", httpParams);
        }
        else{
            jsonObject = null;
        }

        return jsonObject;
    }


}
