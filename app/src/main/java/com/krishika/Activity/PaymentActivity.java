package com.krishika.Activity;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.krishika.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    Button btn;
    TextView status;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize Razorpay SDK
        Checkout.preload(getApplicationContext());

        // Link views
        btn = findViewById(R.id.btn_pay);  // Replace with correct ID
        editText = findViewById(R.id.edit_amount); // Replace with correct ID
        status = findViewById(R.id.paymentStatus); // Replace with correct ID

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int amount = Integer.parseInt(editText.getText().toString());
                    startPayment(amount);
                } catch (NumberFormatException e) {
                    Toast.makeText(PaymentActivity.this, "Enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startPayment(int Amount) {
        Checkout checkout = new Checkout();
                                        // Replace with your actual key

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Sumit Noobda", "Chal Sumit Noobda pay kr");
            jsonObject.put("description", "Buy a food for me");
            jsonObject.put("currency", "INR");
            jsonObject.put("amount", Amount * 100); // Convert to paise

            // Add retry options
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 2); // Optional, maximum retries
            jsonObject.put("retry", retryObj);

            checkout.open(PaymentActivity.this, jsonObject);

        } catch (Exception e) {
            Log.e(TAG, "Error in starting payment", e);
            Toast.makeText(this, "Payment initiation failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        runOnUiThread(() -> status.setText("Success: " + s));
    }

    @Override
    public void onPaymentError(int i, String s) {
        runOnUiThread(() -> status.setText("Error: " + s));
    }
}
