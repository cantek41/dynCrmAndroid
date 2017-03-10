package Data;

import android.content.Context;

import veribis.veribiscrmdyn.BaseActivity;

/**
 * Created by Cantekin on 19.2.2017.
 */

public final class User {
    private String UserId;
    private String PicturePath;
    private String Name;
    private String SurName;
    private String Email;
    private static User userData;

    private String user_name;
    private String password;

    private Context context;


    public static User getUser(Context context) {
        if (userData == null)
            userData = MyPreference.getPreference(context).getUserData();
        return userData;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void clearUser() {
        userData=null;
    }
}
