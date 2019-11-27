package com.goland.gas_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.goland.gas_delivery.Model.Datapage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class order extends AppCompatActivity {

    EditText client_name, client_phone;
    Button order_btn;

    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Datapage datapage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
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

        client_name = (EditText)findViewById(R.id.client_name);
        client_phone = (EditText)findViewById(R.id.client_phone);
        order_btn = (Button)findViewById(R.id.order_btn);

        datapage = new Datapage();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void info(){
        datapage.setName(client_name.getText().toString());
        datapage.setNumber(client_phone.getText().toString());
    }

    public void go(View view){
        String mName = client_name.getText().toString().trim();
        String mPhone = client_phone.getText().toString().trim();

        if (TextUtils.isEmpty(mName)){
            client_name.setError("Required field");
            return;
        }
        if (TextUtils.isEmpty(mPhone)){
            client_phone.setError("Required field");
            return;
        }
        info();
        DatabaseReference postsRef = databaseReference.child("Add");
        DatabaseReference newPostRef = postsRef.push();
        newPostRef.setValue(datapage);

        Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_SHORT).show();
    }

}
