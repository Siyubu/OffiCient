/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.cmu.officient.ui.qr.QRCode_scanner;

public class QRCodeScannerListener implements View.OnClickListener {
    private Context context;

    public QRCodeScannerListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, QRCode_scanner.class);
        context.startActivity(intent);

    }
}
