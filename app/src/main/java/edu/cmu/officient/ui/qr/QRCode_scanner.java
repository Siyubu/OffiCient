/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.cmu.officient.R;

public class QRCode_scanner extends AppCompatActivity {
    private Button btnScan;
    private TextView content;
    private TextView format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);

        btnScan = (Button) findViewById(R.id.btn_scan);
        content = (TextView) findViewById(R.id.content);
        format = (TextView) findViewById(R.id.format);

        btnScan.setOnClickListener(onClickListener());
    }
    
    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();

                    //if you haven't install barcodeScanner app, download it from Google Play
                    downloadScanBarcode();
                }

            }
        };
    }
    private void downloadScanBarcode() {
        Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                format.setText(data.getStringExtra("SCAN_RESULT_FORMAT"));
                content.setText(data.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                format.setText("Press a button to start a scan.");
                content.setText("Scan cancelled.");
            }
        }
    }
}
