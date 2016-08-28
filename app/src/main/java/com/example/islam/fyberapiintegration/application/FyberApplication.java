package com.example.islam.fyberapiintegration.application;

import android.app.Application;

import com.example.islam.fyberapiintegration.dagger.ApplicationComponent;
import com.example.islam.fyberapiintegration.dagger.ApplicationModule;
import com.example.islam.fyberapiintegration.dagger.DaggerApplicationComponent;


/**
 * Created by islam on 8/27/16.
 */
public class FyberApplication extends Application {
    ApplicationComponent applicationComponent;
    private static FyberApplication fyberApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        this.fyberApplication = this;
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    /**
     * we are sure that this instance wont be null in our application
     * as we give it a value in the application onCreate.
     * @return
     */
    public static FyberApplication getInstance() {
        return fyberApplication;
    }
}

