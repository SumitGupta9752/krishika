package com.krishika.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.krishika.Domain.Foods;
import com.krishika.Helper.ManagmentCart;
import com.krishika.R;
import com.krishika.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Foods object;
    private int num = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        // Get intent data
        getIntentExtra();

        // Check if the object is null
        if (object == null) {
            // Handle error (finish or show a message)
            finish();
            return;
        }

        // Set up UI variables
        setVariable();
    }

    private void setVariable() {
        managmentCart=new ManagmentCart(this);
        // Back button functionality
        binding.backBtn.setOnClickListener(v -> finish());

        // Load data into UI elements
        if (object.getImagePath() != null && !object.getImagePath().isEmpty()) {
            Glide.with(this)
                    .load(object.getImagePath())
                    .into(binding.pic);
        } else {
            // If no image, hide the ImageView or set a default image
            binding.pic.setVisibility(View.GONE);
        }

        binding.priceTxt.setText("Rs/-" + object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar() + " Rating");
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalTxt.setText((num * object.getPrice()) + " Rs/-");

        binding.plusBtn.setOnClickListener(v -> {
            num=num+1;
            binding.numTxt.setText(num+"");
            binding.totalTxt.setText("Rs/-"+(num* object.getPrice()));
        });

        binding.minusBtn.setOnClickListener(v -> {
            if(num>1){
                num=num-1;
                binding.numTxt.setText(num+"");
                binding.totalTxt.setText("Rs/-"+(num* object.getPrice()));
            }
        });

        binding.addBtn.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");

        // Add a debug log or error message for null objects
        if (object == null) {
            // Log the issue (optional for debugging)
            // Log.e("DetailActivity", "Received null object in intent.");
        }
    }
}
