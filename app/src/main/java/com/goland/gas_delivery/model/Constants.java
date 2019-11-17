package com.goland.gas_delivery.model;

import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Constants {
        public static final  String MPESA_BASE_URL = "https://sandbox.safaricom.co.ke";
        public static final String CONSUMER_KEY = "7aMAqb9BGP6GUBmqf6zjyNokHivwulBe";
        public static final String CONSUMER_SECRET = 	"kEarXCjKLGeLB5pz";

        //STKPush Properties
        public static final String BUSINESS_SHORT_CODE = "174379";
        public static final String PHONE = "";
        public static final String PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
        public static final String TRANSACTION_TYPE = "CustomerPayBillOnline";
        public static final String PARTYB = "174379";
        public static final String CALLBACK_URL = "https://requestbin.herokuapp.com/1ohtv6v1";

         //Function to get the current timestamp using the yyyyMMddHHmmss format

        public static String getTimestamp() {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        }

         //Function used to obtain the required phone number format

        public static String sanitizePhoneNumber(String phone) {

            if (phone.equals("")) {
                return "";
            }

            if (phone.length() < 11 & phone.startsWith("0")) {
                String p = phone.replaceFirst("^0", "254");
                return p;
            }
            if (phone.length() == 13 && phone.startsWith("+")) {
                String p = phone.replaceFirst("^+", "");
                return p;
            }
            return phone;
        }

         //Function to get the password using the base64 encoding

        public static String getPassword(String businessShortCode, String passkey, String timestamp) {
            String str = businessShortCode + passkey + timestamp;
            //encode the password to Base64
            return Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
        }

}
