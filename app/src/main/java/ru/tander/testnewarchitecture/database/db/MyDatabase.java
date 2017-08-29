package ru.tander.testnewarchitecture.database.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ru.tander.testnewarchitecture.database.entity.User;
import ru.tander.testnewarchitecture.database.dao.UserDao;

/**
 * Created by savchenko on 28.08.17.
 */

@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDao userDao();

     private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context){
        if(instance==null){
            instance = create(context);
        }
        return instance;
    }

    private static MyDatabase create(Context context) {
        RoomDatabase.Builder<MyDatabase>builder = Room.databaseBuilder(context, MyDatabase.class, "users");
        return builder.build();
    }

}


