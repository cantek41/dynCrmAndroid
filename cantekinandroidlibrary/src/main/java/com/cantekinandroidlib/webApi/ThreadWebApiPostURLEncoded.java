package com.cantekinandroidlib.webApi;

import android.os.AsyncTask;

import com.cantekinandroidlib.logger.CustomLogger;

import org.springframework.util.MultiValueMap;

/**
 * post metodunu arka planda çalıştırmak için
 * bunu kullanacak actitivyler ThreadInterface interfacesi ile
 * implemente olmalı, çünkü
 * dönen değer postResult() metodune gönderiliyor.
 * Created by Cantekin on 28.7.2016.
 */

public class ThreadWebApiPostURLEncoded<T> extends AsyncTask<T, String, String> {

    private static final String TAG = "ThreadWebApiPost";
    private IThreadDelegete delegate = null;
    private final MultiValueMap<String, String> map;
    private final String webApiAddres;
    private int requestCode;

    public ThreadWebApiPostURLEncoded(int requestCode, IThreadDelegete delegate, MultiValueMap<String, String> map, String webApiAddres) {
        this.delegate = delegate;
        this.map = map;
        this.webApiAddres = webApiAddres;
        this.requestCode = requestCode;
    }

    @Override
    protected String doInBackground(T... params) {
        return new RestApi<T>(webApiAddres).PostToken(map);
    }

    @Override
    protected void onPostExecute(String result) {
        if (delegate != null) {
            delegate.postResult(result,requestCode);
        } else {
            CustomLogger.error(TAG, "delegate is null");
        }
    }
}
