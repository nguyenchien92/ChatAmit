package com.example.nguyen.chatamit.repositories;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.example.nguyen.chatamit.models.User;

import java.util.ArrayList;
import java.util.List;

public class HandleDataContact {
    private static HandleDataContact mHandle;
    private MutableLiveData mData;
    private List<User> userList = new ArrayList<>();

    public static HandleDataContact getInstance()
    {
        if(mHandle == null)
        {
            mHandle = new HandleDataContact();
        }

        return mHandle;
    }

    public List<User> setDataUser() {
        // This is place add data user....
        createData();

        return userList;
    }

    public List<User> updateData(User user) {

        //check user data is update or edit...

        userList.remove(userList.size() - 1);
        userList.add(userList.indexOf(userList.get(0)), user);

        return userList;
    }


    public MutableLiveData<List<User>> getDataUser() {
        setDataUser();
        mData = new MutableLiveData<>();
        mData.setValue(userList);

        return mData;
    }

    private void createData() {
        if (userList.isEmpty()) {
            userList.add(new User("Chien", 0));
            userList.add(new User("Hoa", 0));
            userList.add(new User("Ly", 0));
            userList.add(new User("Minh", 0));
            userList.add(new User("Hien", 0));
            userList.add(new User("Chau", 0));
        }
    }

}
