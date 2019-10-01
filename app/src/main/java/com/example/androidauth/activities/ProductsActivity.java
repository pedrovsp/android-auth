package com.example.androidauth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidauth.R;
import com.example.androidauth.models.Content;
import com.example.androidauth.models.Product;
import com.example.androidauth.services.ProductAdapter;
import com.example.androidauth.services.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private static final String TAG = "ProductsActivity";
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private List<Content> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        productList = new ArrayList<Content>();
        mRecyclerView = findViewById(R.id.rv_products);
        mAdapter = new ProductAdapter(ProductsActivity.this, productList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ProductsActivity.this));
        findProducts();
    }

    private void findProducts() {
        RetrofitService.getService().getProducts().enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                updateAdapter(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    void updateAdapter(final Product product) {
        mAdapter.setList(product.getContent());
        mAdapter.notifyDataSetChanged();
    }

}
