package com.kostovtd.applinksassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.PendingRow;

public class MainActivity extends AppCompatActivity implements ProductsListener {

    private static final String TAG = MainActivity.class.getSimpleName();


    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        populateDb();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Product> productList = readAllFromDb();
        ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onProductSelected(int productId) {
        Log.d(TAG, "onProductSelected: hit");
        Intent productDetailsIntent = new Intent(this, ProductDetailsActivity.class);
        productDetailsIntent.putExtra("selected_product_id", productId);
        startActivity(productDetailsIntent);
    }


    /**
     * Populate the local DB with some dummy
     * data to work with.
     */
    private void populateDb() {
        Realm realm = Realm.getDefaultInstance();

        if(realm.isEmpty()) {
            Product shampoo = new Product(1, "Shampoo", "The best shampoo EVER",
                    100, 5.99);

            Product soap = new Product(2, "Soap", "Super awesome soap",
                    50, 2.99);

            Product apple = new Product(3, "Apple", "It's a real apple (not the company)",
                    700, 0.99);

            Product orange = new Product(4, "Orange", "The best fruit in da world",
                    1000, 0.99);

            Product banana = new Product(5, "Banana", "Who doesn't like bananas?!",
                    443, 0.55);



            realm.beginTransaction();

            Product realmShampoo = realm.copyToRealm(shampoo);
            Product realmSoap = realm.copyToRealm(soap);
            Product realmApple = realm.copyToRealm(apple);
            Product realmOrange = realm.copyToRealm(orange);
            Product realmBanana = realm.copyToRealm(banana);

            realm.commitTransaction();
        }
    }


    /**
     * Reads all entries in the local DB.
     * @return A {@link List} of {@link Product}.
     */
    private List<Product> readAllFromDb() {
        List<Product> productList = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        productList = realm.where(Product.class).findAll();

        return productList;
    }
}
