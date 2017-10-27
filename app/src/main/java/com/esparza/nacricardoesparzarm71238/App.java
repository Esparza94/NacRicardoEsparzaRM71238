package com.esparza.nacricardoesparzarm71238;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Esparza on 10/27/2017.
 */

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
