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

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.List;

import edu.cmu.officient.R;
import info.androidhive.barcode.BarcodeReader;

import android.os.Bundle;

public class QRCodeScanner extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{
    public final static int QR_CODE_READER = 4501;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {
        Intent data = new Intent();
        data.putExtra("code", barcode.displayValue);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {
        // multiple barcodes scanned
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        // barcode scanned from bitmap image
    }

    @Override
    public void onScanError(String s) {
        // scan error
    }

    @Override
    public void onCameraPermissionDenied() {
        // camera permission denied
    }
}
