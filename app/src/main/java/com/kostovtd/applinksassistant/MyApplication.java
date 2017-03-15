package com.kostovtd.applinksassistant;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;


public class MyApplication extends Application{

    private static final MyApplication ourInstance = new MyApplication();

    public static MyApplication getInstance() {
        return ourInstance;
    }


    private List<Product> productList;


    private MyApplication() {

    }


    @Override
    public void onCreate() {
        super.onCreate();


        // CREATE & ADD SOME DUMMY PRODUCTS IN OUR SUPER MARKET
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


        productList = new ArrayList<>();
        productList.add(shampoo);
        productList.add(soap);
        productList.add(apple);
        productList.add(orange);
        productList.add(banana);
    }


    public List<Product> getProductList() {
        return productList;
    }
}
