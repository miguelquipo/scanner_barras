    package com.wanuchionfire.scanner_barras;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.wanuchionfire.scanner_barras.databinding.ActivityMainBinding;

    public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {

       if (result.getContents()== null){
           Toast.makeText(this, "No se a encontrado", Toast.LENGTH_SHORT).show();
       }else {
           binding.etCodigo.setText(result.getContents());
       }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLeerCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escanear();
            }
        });
    }
    public void cambirConsulta(View v){
        Intent intent = new Intent(MainActivity.this,BusquedaActivity.class);
        startActivity(intent);
    }

        private void escanear() {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanear codigo");
            options.setCameraId(0);
            options.setOrientationLocked(false);
            options.setBeepEnabled(true);
            options.setCaptureActivity(CaptureActivityPortraint.class);
            options.setBarcodeImageEnabled(false);

            barcodeLauncher.launch(options);
        }
    }