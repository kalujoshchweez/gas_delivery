package com.goland.gas_delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText editUserName, email, password;

    private String userId;
    private String emailId;
    private Button buttonSignup;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    public void RegisterLogin (View view){
        if (view.getId()==R.id.buttonLog){
            Intent intent = new Intent(Registration.this, SupplierLogin.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        editUserName = (EditText)findViewById(R.id.editUserName);
        buttonSignup = (Button)findViewById(R.id.buttonSignup);
        //stuff

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memail = email.getText().toString().trim();
                String mpass = password.getText().toString().trim();

                if (TextUtils.isEmpty(memail)){
                    email.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty(mpass)){
                    password.setError("Required Field");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(memail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), UserLogin.class));
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();

                        }
                    }
                });
            }
        });
    }
}
