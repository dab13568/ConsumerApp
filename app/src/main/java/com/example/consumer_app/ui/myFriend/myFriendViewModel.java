package com.example.consumer_app.ui.myFriend;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class myFriendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public myFriendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}