package Data;

import android.content.Context;

import veribis.veribiscrmdyn.BaseActivity;
import veribis.veribiscrmdyn.MainActivity;

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

    private String access_token;
    private String token_type;
    private String expires_in;

    private Context context;


    public static User getUser(Context context) {
        if (userData == null)
            userData = MyPreference.getPreference(context).getUserData();
        if (userData == null && context instanceof BaseActivity)
            ((BaseActivity) context).userFaild();
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
