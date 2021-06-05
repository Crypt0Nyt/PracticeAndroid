package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.databinding.ListViewBinding;
import com.example.helloworld.model.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ProductViewHolder> {

    //For Context
    private final Context context;

//    For the list of items data
    private final List<Item> items;
    private List<Item> visibleItems;

    public ItemAdapter(Context context, List<Item> items){

        this.context = context;
        this.items = items;
        this.visibleItems = items;
    }

    @NonNull
    @NotNull
    @Override

//    Inflate layout and return viewHolder
    public ProductViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        Inflate Layout
        ListViewBinding binding = ListViewBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ProductViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.ProductViewHolder holder, int position) {

        Item mItem = visibleItems.get(position);
        //Bind Data
        holder.b.checkbox.setText(mItem.name);

//        Handle check changes
        holder.b.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> visibleItems.get(position).isChecked = isChecked);

        //Restore previous checked state
        holder.b.checkbox.setChecked(mItem.isChecked);
    }

    @Override
//    Size
    public int getItemCount() {
        return visibleItems.size();
    }

    public void filter(String query){
//        No query, show all times
        if(query.trim().isEmpty()){
            visibleItems = items;
            notifyDataSetChanged();
            return;
        }

//        Filter and add to visibleItems
        List<Item> temp = new ArrayList<>();
        query = query.toLowerCase();

        for(Item item : items)
            if(item.name.toLowerCase().contains(query))
                temp.add(item);

            visibleItems = temp;
//            Refresh List
        notifyDataSetChanged();
    }

//  ViewHolder
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ListViewBinding b;

        public ProductViewHolder (ListViewBinding b){
            super(b.getRoot());
            this.b = b;
        }
    }
}
