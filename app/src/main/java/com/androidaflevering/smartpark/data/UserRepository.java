package com.androidaflevering.smartpark.data;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.androidaflevering.smartpark.primitivDatabase.Database;
import com.androidaflevering.smartpark.primitivDatabase.SamarbejdspartnereInfo;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UserRepository {
    private final UserLiveData currentUser;
    private final Application app;
    private static UserRepository instance;
    private static Database database;

    private UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
        database = Database.getInstance();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public ArrayList<SamarbejdspartnereInfo> getAList() {
        return database.getSamarbejdspartnereAListe();
    }
}
