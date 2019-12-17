package com.goland.gas_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gas_delivery.R;

public class order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        myButtonLisner();

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        //create an array adapter for spinner 1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gas_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //spinner 2
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.gas_weight,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter((adapter2));


    }
    public void onItemSelected(AdapterView<?> parent, View view ,int pos,long id){

        //retrieve item
        parent.getItemAtPosition(pos).toString();
        String price_cat;
        if(pos == 0){
            price_cat="Ksh. 1000";
        }else{
            price_cat="Ksh. 2500";
        }
        final TextView price = (TextView) findViewById(R.id.price);
        price.setText(price_cat);
    }
    public  void onNothingSelected(AdapterView<?> parent){
        //another action

    }

    public  void  myButtonLisner(){
        Button orderButton = (Button) findViewById(R.id.order_btn);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}