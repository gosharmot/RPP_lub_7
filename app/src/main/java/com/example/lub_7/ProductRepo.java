package com.example.lub_7;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepo {

    private final ProductDao productDao;

    private final SingleLiveData<ProductEntity> productEntityMutableLiveData;

    public ProductRepo(Context context) {

        ProductDB productDB = ProductDB.getInstance(context);
        productDao = productDB.productDao();

        productEntityMutableLiveData = new SingleLiveData<>();
    }

    //executorService - это ассинхронная работа с БД

    public void insertProduct(ProductEntity productEntity){
        ProductDB.executorService.submit(() -> productDao.addProduct(productEntity));
    }

   public LiveData<List<ProductEntity>> getProductList(){
        return productDao.selectAllProducts();
    }

    public LiveData<ProductEntity> getProductById(int id){
        ProductDB.executorService.submit(() -> productEntityMutableLiveData.postValue( productDao.getProduct(id)));

        return productEntityMutableLiveData;
    }

   public void deleteProduct(int id){
        ProductDB.executorService.submit(()-> productDao.deleteProduct(id));
    }

    public void updateProduct(ProductEntity productEntity){
        ProductDB.executorService.submit(() -> productDao.updateProduct(productEntity));
    }

}
