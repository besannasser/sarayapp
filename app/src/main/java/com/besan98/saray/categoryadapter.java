package com.besan98.saray;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class categoryadapter extends RecyclerView.Adapter<categoryadapter.viewHolder> {
private List<categorymodel> categorymodelList;

    public categoryadapter(List<categorymodel> categorymodelList) {

        this.categorymodelList = categorymodelList;
    }

    @NonNull
    @Override
    public categoryadapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryrecycle,parent,false);


        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryadapter.viewHolder holder, int position) {
       String icon = categorymodelList.get(position).getCategoryicon();

    }

    @Override
    public int getItemCount() {
        return categorymodelList.size();
    }
    public  class viewHolder extends RecyclerView.ViewHolder{
private ImageView categoryicon;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            categoryicon = itemView.findViewById(R.id.categoryicon);
        }
        private void setCategoryicon(){
        }

    }
}
