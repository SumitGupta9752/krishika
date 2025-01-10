package com.krishika.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.krishika.Activity.ListFoodsActivity;
import com.krishika.Domain.Category;
import com.krishika.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext(); // Initialize context here
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate); // Correctly returning ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());

        // Set background resource based on position
        switch (position) {
            case 0: {
                holder.pic.setBackgroundResource(R.drawable.cat_1_background);
                break;
            }
            case 1: {
                holder.pic.setBackgroundResource(R.drawable.cat_2_background);
                break;
            }
            case 2: {
                holder.pic.setBackgroundResource(R.drawable.cat_3_background);
                break;
            }
            case 3: {
                holder.pic.setBackgroundResource(R.drawable.cat_4_background);
                break;
            }
            case 4: {
                holder.pic.setBackgroundResource(R.drawable.cat_5_background);
                break;
            }
            case 5: {
                holder.pic.setBackgroundResource(R.drawable.cat_6_background);
                break;
            }
            case 6: {
                holder.pic.setBackgroundResource(R.drawable.cat_7_background);
                break;
            }
        }

        // Load the image using Glide
        int drawableResourceId = context.getResources().getIdentifier(
                items.get(position).getImagePath(), "drawable", holder.itemView.getContext().getPackageName()
        );

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListFoodsActivity.class);
//                intent.putExtra("CategoryId", items.get(position).getId());
                intent.putExtra("CategoryId", position);
                intent.putExtra("CategoryName", items.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder class to bind views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);
        }
    }
}
