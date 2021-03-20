package com.app.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AccDetailActivity extends AppCompatActivity implements PaymentResultListener {
    TextView textView_detailname, textView_detailaddress, textView_rentdetail;
    Button button_pay;
    String name, address, rent;
    int amt;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_detail);
        textView_detailname = findViewById(R.id.textView_detailname);
        textView_detailaddress = findViewById(R.id.textView_detailaddress2);
        textView_rentdetail = findViewById(R.id.textView_rentdetail);
        button_pay = findViewById(R.id.button_pay);



        Checkout.preload(getApplicationContext());


        Intent intent = getIntent();
        name = intent.getStringExtra("acc_name");
        address = intent.getStringExtra("address");
        rent = intent.getStringExtra("rent");
        Toast.makeText(this, ""+address, Toast.LENGTH_SHORT).show();

        textView_detailname.setText(name);
        textView_detailaddress.setText(address);
        textView_rentdetail.setText(rent);
        amt = Integer.parseInt(rent)*100;

        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccDetailActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                startPayment();
            }
        });
    }

    public void startPayment() {


        Checkout checkout = new Checkout();


        checkout.setKeyID("rzp_test_K8FFBsTGdcCS9Z");


        //checkout.setImage(R.drawable.logo);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Accommodation Booking");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amt);//pass amount in currency subunits
            options.put("prefill.email", "weirdsapiens@gmail.com");
            //options.put("prefill.contact","8299811737");

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("razorpay", "Error in starting Razorpay Checkout", e);
        }

        
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
        writeBookings();

        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {

    }

    public void writeBookings() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time = simpledateformat.format(calendar.getTime());

        Map<String, String> data = new HashMap<>();
        data.put("user_id", ""+FirebaseAuth.getInstance().getCurrentUser().getUid());
        data.put("user_name","test");
        data.put("acc_name", name);
        data.put("acc_address", address);
        data.put("acc_type", "test");
        data.put("booking_time", time);
        data.put("booking_id", "test");
        data.put("rent", rent);

        db.collection("bookings")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }
}