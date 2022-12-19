package com.example.digitalstudentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pdfocr extends AppCompatActivity {

    Button OCR, PDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfocr);

        OCR = findViewById(R.id.ocrBtn);
        PDF = findViewById(R.id.pdfBtn);

        PDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pdfocr.this, PDFViewer.class));
            }
        });

        OCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pdfocr.this,ocr.class));
            }
        });
    }
}