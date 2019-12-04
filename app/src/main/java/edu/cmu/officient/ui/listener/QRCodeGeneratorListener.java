/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : ECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.cmu.officient.ui.qr.QRGenerator;

public class QRCodeGeneratorListener implements View.OnClickListener {
    private Context ctx;

    public QRCodeGeneratorListener( Context cxt) {
        this.ctx=cxt;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ctx, QRGenerator.class);
        ctx.startActivity(intent);

    }
}
