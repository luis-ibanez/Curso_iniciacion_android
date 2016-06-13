package com.geekshubs.recyclerexample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.geekshubs.recyclerexample.R;
import com.geekshubs.recyclerexample.ds.model.Product;

import java.util.List;

/**
 * Created by geekshubs on 17/04/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VhProduct> {

    List<Product> productList;

    public ProductAdapter(List<Product> productList){
        this.productList = productList;
    }

    @Override
    public VhProduct onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_product, parent, false);
        return new VhProduct(v);
    }

    @Override
    public void onBindViewHolder(VhProduct holder, int position) {
        holder.quantity.setText(""+productList.get(position).getQuantity());
        holder.name.setText(productList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class VhProduct extends RecyclerView.ViewHolder {

        TextView name;
        EditText quantity;

        public VhProduct(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.listitem_name);
            this.quantity = (EditText) itemView.findViewById(R.id.listitem_quantity);
        }
    }
}
