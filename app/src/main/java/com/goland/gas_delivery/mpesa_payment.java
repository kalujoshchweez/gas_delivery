package com.goland.gas_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.goland.gas_delivery.model.AccessToken;
import com.goland.gas_delivery.model.Constants;
import com.goland.gas_delivery.model.NetworkUtil;
import com.goland.gas_delivery.model.STKPush;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class mpesa_payment extends AppCompatActivity {
    private Button mpesa_submit;
    //private ProgressBar progressBar;
    private EditText phone_no,mpesa_amount;
    private AccessToken newAccessToken;
    private STKPush stkPush;
    private CompositeDisposable compositeDisposable;
    private String phone,amount;
    String sanitizedphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa_payment);
        compositeDisposable = new CompositeDisposable();
        mpesaButtonListener();

    }
   // @Override
    public void mpesaButtonListener(){
        mpesa_submit = (Button) findViewById(R.id.mpesa_submitbtn);
        phone_no = (EditText) findViewById(R.id.mpesa_phoneNo);
        mpesa_amount = (EditText) findViewById(R.id.mpesa_amount);

        mpesa_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phone_no.getText().toString();
                amount =  mpesa_amount.getText().toString();

                //using rxjava to perform an asynchronous call to the mpesa aoth endpoint
                compositeDisposable.add(
                        NetworkUtil.getRetrofit()
                                .getAccessToken()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Consumer<AccessToken>() {
                                    @Override
                                    public void accept(AccessToken accessToken) throws Exception {
                                        handleAccessTokenResponse(accessToken);
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        handleAccessTokenException(throwable);
                                    }
                                }));
            }

        });
    }


    private void handleAccessTokenResponse(AccessToken accessToken){
        newAccessToken = new AccessToken(accessToken.getAccessToken(),accessToken.getExpiresIn());


        stkPush = new STKPush(
                Constants.BUSINESS_SHORT_CODE,
                Constants.getPassword(Constants.BUSINESS_SHORT_CODE,Constants.PASSKEY,Constants.getTimestamp()),
                Constants.getTimestamp(),
                Constants.TRANSACTION_TYPE,
                amount,
                phone,
                Constants.PARTYB,
                phone,
                Constants.CALLBACK_URL,
                "test",
                "test"
        );

        if (newAccessToken.getAccessToken() != null){

              //Using Rxjava to perform an asynchronous call to the mpesa stkpush endpoint

            compositeDisposable.add(
                    NetworkUtil.getRetrofit(newAccessToken.getAccessToken())
                            .sendPush(stkPush)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<STKPush>() {
                                @Override
                                public void accept(STKPush stkPush) throws Exception {
                                    handleSTKResponse(stkPush);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    handleSTKException(throwable);
                                }
                            }));
        }
    }
    private void handleAccessTokenException(Throwable throwable) {

         // Log the access token request exception
        try {
            // code that might throw an exception
        } catch (Exception e) {
            Log.e("MYAPP", "exception", e);
        }

    }

    private void handleSTKResponse(STKPush stkPush) {

    }

    private void handleSTKException(Throwable throwable) {

         //Log the STK request exception
        try {
            // code that might throw an exception
        } catch (Exception e) {
            Log.e("stk", "exception", e);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
          //Dispose of component disposable to avoid memory leak
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }



}
