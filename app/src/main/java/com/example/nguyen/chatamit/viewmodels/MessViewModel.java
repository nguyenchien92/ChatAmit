package com.example.nguyen.chatamit.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nguyen.chatamit.models.User;
import com.example.nguyen.chatamit.repositories.HandleDataMess;

import java.util.List;

public class MessViewModel extends ViewModel {

    private HandleDataMess mHandles;
    private MutableLiveData<List<User>> mutableLiveData;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init()
    {
        if(mHandles != null)
        {
            return;
        }

        mHandles = HandleDataMess.getInstance();
        mutableLiveData = mHandles.getDataUser();
    }

    public void addNewValues(final User user)
    {
        // add data use when having an event add data user occur...
        // and back update state mIsUpdating.
    }

    public MutableLiveData<List<User>> getDataUser()
    {
        return mutableLiveData;
    }

    public void updateUser(User user)
    {
        mutableLiveData.postValue(mHandles.updateData(user));
    }

    public MutableLiveData<Boolean> getStateUpdate()
    {
        return mIsUpdating;
    }

}
