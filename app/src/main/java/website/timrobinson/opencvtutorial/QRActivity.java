package website.timrobinson.opencvtutorial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class QRActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);

        getSupportActionBar().hide();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("resultttttt", "" + result);
                        if (result.getText().equals("locialappwashereeeee")) {
                         //   Toast.makeText(getApplicationContext(), "QR Kod Başarılı bir Şekilde Okundu", Toast.LENGTH_SHORT).show();

                            GlobalData globalData = new GlobalData();
                            globalData.setQRCode(true);

                            Intent intent = new Intent(getApplicationContext(), Map.class);
                            intent.putExtra("Redirect", true);
                            startActivity(intent);

                        }else{

                            GlobalData globalData = new GlobalData();
                            globalData.setQRCode(false);

                            Intent intent = new Intent(getApplicationContext(), Map.class);
                            intent.putExtra("Redirect", true);
                            startActivity(intent);

                        }

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
