package ru.tander.testnewarchitecture.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.tander.testnewarchitecture.database.entity.User;

/**
 * Created by savchenko on 28.08.17.
 */

@Dao
public interface UserDao {
    @Query("select * from users order by id")
    LiveData<List<User>> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(User... users);

    @Query("delete from users")
    void deleteUsers();
}