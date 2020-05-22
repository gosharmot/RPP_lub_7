package com.example.lub_7;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderRepo {

    private final OrderDao orderDao;

    private final SingleLiveData<OrderEntity> getOrder;

    public OrderRepo(Context context) {
        ProductDB productDB = ProductDB.getInstance(context.getApplicationContext());
        orderDao = productDB.orderDao();

        getOrder = new SingleLiveData<>();
    }

    //executorService - это ассинхронная работа с БД

    public void insertOrder(OrderEntity orderEntity){
        ProductDB.executorService.submit(()-> orderDao.addProduct(orderEntity));
    }

    public LiveData<List<OrderEntity>> getListOrder(){
        return orderDao.selectAllProducts();
    }

    public LiveData<OrderEntity> getOrderById(int id){
        ProductDB.executorService.submit(() -> getOrder.postValue(orderDao.getOrderById(id)));

        return getOrder;
    }

    public void updateOrder(OrderEntity orderEntity){
        ProductDB.executorService.submit(() -> orderDao.updateOrder(orderEntity));
    }


    public void deleteAllOrder(){
        ProductDB.executorService.submit(orderDao::delete);
    }

}
