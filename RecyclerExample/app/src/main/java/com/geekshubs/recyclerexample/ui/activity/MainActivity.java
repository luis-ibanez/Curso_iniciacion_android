package com.geekshubs.recyclerexample.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geekshubs.recyclerexample.R;
import com.geekshubs.recyclerexample.ds.db.DbSqliteHelper;
import com.geekshubs.recyclerexample.ds.model.Product;
import com.geekshubs.recyclerexample.ui.adapter.ProductAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private DbSqliteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbSqliteHelper(this);

        loadProductsInDb();

        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(getDbProducts());
        recyclerView.setAdapter(adapter);

    }

    public List<Product> getDbProducts(){
        return dbHelper.getAllProducts();
    }

    public void loadProductsInDb(){
        dbHelper.createProduct(new Product("Pan", 1));
        dbHelper.createProduct(new Product("Agua", 1));
        dbHelper.createProduct(new Product("Leche", 1));
        dbHelper.createProduct(new Product("Queso", 1));
        dbHelper.createProduct(new Product("Escoba", 1));
        dbHelper.createProduct(new Product("Cubo", 1));
        dbHelper.createProduct(new Product("Piruleta", 1));
        dbHelper.createProduct(new Product("champu", 1));
        dbHelper.createProduct(new Product("gel", 1));
    }
}
