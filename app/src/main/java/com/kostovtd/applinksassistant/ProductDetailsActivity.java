package com.kostovtd.applinksassistant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
    }
}
