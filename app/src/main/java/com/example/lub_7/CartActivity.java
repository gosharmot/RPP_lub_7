package com.example.lub_7;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    private CartAdapter mainAdapter;

    private RecyclerView recyclerView;

    private OrderRepo orderRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button purchaseBtn = findViewById(R.id.purchase);

        orderRepo = new OrderRepo(this.getApplicationContext());

        recyclerView = findViewById(R.id.shopping_cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderRepo.getListOrder().observe(this, orderEntities -> {
            mainAdapter = new CartAdapter(orderEntities);
            recyclerView.setAdapter(mainAdapter);
        });

        purchaseBtn.setOnClickListener(v -> new Handler().postDelayed(() -> {
            orderRepo.deleteAllOrder();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }, 2000));

    }

}
