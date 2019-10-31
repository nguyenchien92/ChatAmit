package com.example.nguyen.chatamit.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.models.User;

import java.util.ArrayList;
import java.util.List;

public class HandleDataMess {
    private static HandleDataMess mHandle;
    private List<User> mUser = new ArrayList<>();

    public static HandleDataMess getInstance() {
        if (mHandle == null) {
            mHandle = new HandleDataMess();
        }
        return mHandle;
    }

    public List<User> setDataUser() {
        // This is place add data user....
        createData();

        return mUser;
    }

    public List<User> updateData(User user) {

        //check user data is update or edit...

        mUser.remove(mUser.size() - 1);
        mUser.add(mUser.indexOf(mUser.get(0)), user);

        return mUser;
    }


    public MutableLiveData<List<User>> getDataUser() {
        setDataUser();
        MutableLiveData<List<User>> data = new MutableLiveData<>();
        data.setValue(mUser);

        return data;
    }

    private void createData() {
        if (mUser.isEmpty()) {
            mUser.add(new User("Chien", "20/08/2019", "hola hola", R.drawable.ic_user_profile_gray, 1));
            mUser.add(new User("Chien2", "20/08/2019", "hola hola", R.drawable.ic_user_profile_gray, 1));
            mUser.add(new User("Chien3", "20/08/2019", "hola hola", R.drawable.ic_user_profile_gray, 1));

            mUser.add(new User("Chien4", "20/08/2019", "hola hola", R.drawable.ic_user_group_gray, 1));
            mUser.add(new User("Chien5", "20/08/2019", "hola hola", R.drawable.ic_user_group_gray, 1));
            mUser.add(new User("Chien6", "20/08/2019", "hola hola", R.drawable.ic_user_group_gray, 1));
        }
    }

}
