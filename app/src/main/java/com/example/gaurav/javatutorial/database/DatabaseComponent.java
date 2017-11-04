package com.example.gaurav.javatutorial.database;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by gaurav on 9/3/17.
 */
@Singleton
@Component(modules={DatabaseModule.class})
public interface DatabaseComponent {

    void inject(ProgramTable programTable);
}
