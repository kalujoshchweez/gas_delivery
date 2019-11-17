package com.goland.gas_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class choose_method_of_payment extends AppCompatActivity {
    private Button mpesaBtn,easypayBtn;
   // private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_method_of_payment);

        mpesaBtn = (Button) findViewById(R.id.mpesa_payment_btn);

        mpesaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choose_method_of_payment.this,mpesa_payment.class));
            }
        });
    }
}
