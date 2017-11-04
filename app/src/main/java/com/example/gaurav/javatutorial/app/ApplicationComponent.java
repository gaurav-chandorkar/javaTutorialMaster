package com.example.gaurav.javatutorial.app;

import com.example.gaurav.javatutorial.MainActivity;
import com.example.gaurav.javatutorial.asynctask.ProgramIntoDBAsync;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gaurav on 8/30/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity mainActivity);

}
