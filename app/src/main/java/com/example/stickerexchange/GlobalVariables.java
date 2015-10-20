package com.example.stickerexchange;

import android.app.Application;

/**
 * Created by vukan.boskovic on 14.10.2015..
 * Ment to get and set current user id from DB
 */
public class GlobalVariables extends Application {

    private static  String userID;

    public static String getUserID(){
        return userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
}
