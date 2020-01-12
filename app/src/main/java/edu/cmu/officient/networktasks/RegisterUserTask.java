/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.networktasks;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import edu.cmu.officient.model.User;
import edu.cmu.officient.ui.users.SuccessfulRegistration;

public class RegisterUserTask extends StandardRequestTask {
    private String username, password;
    public RegisterUserTask(AppCompatActivity activity, View root, View progressMarker, String ... args) {
        super(activity, root, progressMarker);
        username = args[0];
        password = args[1];
    }

    @Override
    protected String getOutput() {
        try {
            return getData().getString("message");
        }
        catch (JSONException e) {
            return  "error";
        }
    }

    @Override
    protected void processData(String s) {
        if(s.equalsIgnoreCase("already_registered")){
            Toast.makeText(getBaseActivity(), "User is already registered", Toast.LENGTH_SHORT).show();
        }
        else{
            // Save to Firebase
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(username + User.EMAIL_SUFFIX, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Map<String, Object> data = new HashMap<>();
                            try {
                                data.put("id", getData().getInt("data"));
                            }
                            catch (JSONException e) {
                                Log.e("ERR", getData().toString());
                                data.put("id", -1);
                            }
                            data.put("andrew_id", username);
                            data.put("faculty", false);
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("users")
                                    .document(username)
                                    .set(data);
                        }
                    });
            Intent intent = new Intent(getBaseActivity(), SuccessfulRegistration.class);
            getBaseActivity().startActivity(intent);
            getBaseActivity().finish();
        }

    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"signup", "andrewId", "password"};
    }
}
