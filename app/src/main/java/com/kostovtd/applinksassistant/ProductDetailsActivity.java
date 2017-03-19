package com.kostovtd.applinksassistant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductDetailsActivity extends AppCompatActivity {

    private static final String TAG = ProductDetailsActivity.class.getSimpleName();


    private TextView textProductName;
    private TextView textProductDescription;
    private TextView textProductQuantity;
    private TextView textProductPrice;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // INITIALIZE VIEWS
        textProductName = (TextView) findViewById(R.id.text_product_name);
        textProductDescription = (TextView) findViewById(R.id.text_product_description);
        textProductQuantity = (TextView) findViewById(R.id.text_quantity);
        textProductPrice = (TextView) findViewById(R.id.text_price);


        if(getIntent().getExtras() != null) {
            if(getIntent().getExtras().containsKey("selected_product_id")) {
                int selectedProductId = getIntent().getExtras().getInt("selected_product_id");
                Product product = findProductById(selectedProductId);
                showProductData(product);
            }
        }
    }


    /**
     * Searches the local DB for a record with
     * a given Id.
     * @param productId The Id of a product.
     * @return An object of type {@link Product}.
     */
    private Product findProductById(int productId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Product> results = realm.where(Product.class)
                .equalTo("id", productId)
                .findAll();

        Product product = results.first();

        return product;
    }


    /**
     * Populates the current {@link android.view.View}s with
     * the corresponding {@link Product} data.
     * @param product
     */
    private void showProductData(Product product) {
        if(product != null) {
            // PRODUCT NAME
            String productName = product.getName();
            boolean isNameEmpty = productName.isEmpty();
            if(!isNameEmpty) {
                textProductName.setText(productName);
            }


            // PRODUCT DESCRIPTION
            String productDescription = product.getDescription();
            boolean isDescriptionEmpty = productDescription.isEmpty();
            if(!isDescriptionEmpty) {
                textProductDescription.setText(productDescription);
            }


            // PRODUCT QUANTITY
            int productQuantity = product.getStockQuantity();
            String productQuantityStr = String.valueOf(productQuantity);
            textProductQuantity.setText(productQuantityStr);


            // PRODUCT PRICE
            double productPrice = product.getPrice();
            String productPriceStr = String.valueOf(productPrice);
            textProductPrice.setText(productPriceStr);
        }

    }
}
