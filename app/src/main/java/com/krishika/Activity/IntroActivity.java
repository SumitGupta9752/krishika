package com.krishika.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.krishika.databinding.ActivityIntroBinding; // Import the generated binding class

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater()); // Inflate the binding object
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot()); // Use the binding to set content view

        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5"));
    }

    private void setVariable(){
        binding.loginBtn.setOnClickListener(v -> {
            if (mAuth.getCurrentUser()!=null){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }else {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
            // Handle login button click
        });

        binding.signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(IntroActivity.this, SignupActivity.class));
            // Handle signup button click
        });
    }
}