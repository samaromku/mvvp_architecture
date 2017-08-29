package ru.tander.testnewarchitecture.ui;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import ru.tander.testnewarchitecture.database.dao.UserDao;
import ru.tander.testnewarchitecture.database.db.MyDatabase;
import ru.tander.testnewarchitecture.database.entity.User;

//import ru.tander.testnewarchitecture.database.db.MyDatabase;

/**
 * Created by savchenko on 28.08.17.
 */

public class MainViewModel extends AndroidViewModel{
    private static final String TAG = "MainViewModel";
    private MutableLiveData<User> user;
    private UserDao userDao;

    public MainViewModel(Application application){
        super(application);
        userDao = MyDatabase.getInstance(application).userDao();
    }


    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<List<User>>getUsers(){
        return userDao.getUsers();
    }

    public void addUser(User... user){
        userDao.add(user);
    }

    public void deleteAll(){
        userDao.deleteUsers();
    }

}
