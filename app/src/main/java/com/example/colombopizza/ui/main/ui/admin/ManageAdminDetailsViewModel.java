package com.example.colombopizza.ui.main.ui.admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageAdminDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ManageAdminDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}