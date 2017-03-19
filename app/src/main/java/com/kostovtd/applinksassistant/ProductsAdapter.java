package com.kostovtd.applinksassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private static final String TAG = ProductsAdapter.class.getSimpleName();

    private Context mContext;
    private List<Product> mData;
    private ProductsListener listener;


    public ProductsAdapter(Context context, List<Product> data) {
        this.mContext = context;
        this.mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product currentProduct = mData.get(position);

        if(currentProduct != null) {
            String productNameStr = currentProduct.getName();
            holder.textProductName.setText(productNameStr);

            int productQuantity = currentProduct.getStockQuantity();
            holder.textProductQuantity.setText(String.valueOf(productQuantity));

            double productPrice = currentProduct.getPrice();
            holder.textProductPrice.setText(String.valueOf(productPrice));

            if(listener != null) {
                holder.rootContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int productId = currentProduct.getId();
                        listener.onProductSelected(productId);
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setListener(ProductsListener listener) {
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout rootContainer;
        public TextView textProductName;
        public TextView textProductQuantity;
        public TextView textProductPrice;


        public ViewHolder(View itemView) {
            super(itemView);

            rootContainer = (LinearLayout) itemView.findViewById(R.id.root_container);
            textProductName = (TextView) itemView.findViewById(R.id.text_product_name);
            textProductQuantity = (TextView) itemView.findViewById(R.id.text_quantity);
            textProductPrice = (TextView) itemView.findViewById(R.id.text_price);

        }
    }
}
