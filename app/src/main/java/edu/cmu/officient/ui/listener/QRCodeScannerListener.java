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

import androidx.appcompat.app.AppCompatActivity;

import edu.cmu.officient.ui.qr.QRCodeScanner;

public class QRCodeScannerListener implements View.OnClickListener {
    private AppCompatActivity parentActivity;

    public QRCodeScannerListener(AppCompatActivity activity)
    {
        parentActivity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(parentActivity, QRCodeScanner.class);
        parentActivity.startActivityForResult (intent, QRCodeScanner.QR_CODE_READER);
    }
}
