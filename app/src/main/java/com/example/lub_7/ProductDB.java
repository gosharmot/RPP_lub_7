package com.example.lub_7;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Инициализация БД.

//ProductEntity.class, OrderEntity.class это таблицы, которые будут созданы при создании БД.

@Database(entities = {ProductEntity.class, OrderEntity.class}, version = 1)
public abstract class ProductDB extends RoomDatabase {

    private static volatile ProductDB INSTANCE;
    public static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static ProductDB getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (ProductDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDB.class,
                            "ProductDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract ProductDao productDao();
    public abstract OrderDao orderDao();

}
