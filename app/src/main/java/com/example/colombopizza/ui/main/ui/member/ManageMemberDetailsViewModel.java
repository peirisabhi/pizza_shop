package com.example.colombopizza.ui.main.ui.member;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageMemberDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ManageMemberDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}