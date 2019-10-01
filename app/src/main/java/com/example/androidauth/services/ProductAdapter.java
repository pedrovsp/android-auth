package com.example.androidauth.services;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidauth.R;
import com.example.androidauth.models.Content;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;

    List<Content> list;

    private LayoutInflater mInflater;

    private static final String TAG = "ProductAdapter";

    public ProductAdapter(Context context, List<Content> list) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    public List<Content> getList() {
        return list;
    }

    public void setList(List<Content> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View linha = mInflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(linha, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        Content produto = list.get(position);
        holder.priceView.setText(String.valueOf(produto.getPrice()));
        holder.descView.setText(produto.getDescription());
        holder.nameView.setText(produto.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameView, descView, priceView;
        public final ProductAdapter mAdapter;

        public ProductViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_label);
            descView = itemView.findViewById(R.id.desc_label);
            priceView = itemView.findViewById(R.id.price_label);
            this.mAdapter = adapter;
        }
    }
}
