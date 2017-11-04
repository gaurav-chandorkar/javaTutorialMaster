package com.example.gaurav.javatutorial.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by gaurav on 8/21/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TAG="MyService";
    @Override
    public void onTokenRefresh() {
       // super.onTokenRefresh();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh: "+refreshToken);
    }
}
