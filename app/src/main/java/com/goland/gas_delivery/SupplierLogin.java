package com.goland.gas_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SupplierLogin extends AppCompatActivity implements View.OnKeyListener{

    EditText editSupplierLogin, editSupplierPassword;
    Button buttonSupplierLogin;
    private ProgressDialog mDialog;

    @Override
    public boolean onKey(View v, int keycode, KeyEvent event){
        if (keycode == KeyEvent.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN){
            next(v);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_login);

        editSupplierLogin = (EditText)findViewById(R.id.editSupplierLogin);
        editSupplierPassword = (EditText)findViewById(R.id.editSupplierPassword);
        buttonSupplierLogin = (Button)findViewById(R.id.buttonSupplierLogin);

        buttonSupplierLogin.setOnKeyListener(this);
        mDialog = new ProgressDialog(this);
    }

    public void next(View v){
        String name = editSupplierLogin.getText().toString();
        String pass = editSupplierPassword.getText().toString();

        mDialog.setMessage("Processing...");
        mDialog.show();

        if (name.equals("Supplier1")&&pass.equals("letsgo1234")){
            Intent intent = new Intent(SupplierLogin.this, MainActivity.class);
            startActivity(intent);
            mDialog.dismiss();
        }
        if (name.equals("Supplier2")&&pass.equals("gogogog2345")){
            Intent intent = new Intent(SupplierLogin.this, MainActivity.class);
            startActivity(intent);
            mDialog.dismiss();
        }
        if (name.equals("Supplier3")&&pass.equals("ggfhgdyu568")){
            Intent intent = new Intent(SupplierLogin.this, MainActivity.class);
            startActivity(intent);
            mDialog.dismiss();
        }
    }
}
