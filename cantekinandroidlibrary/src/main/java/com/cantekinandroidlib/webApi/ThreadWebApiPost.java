package com.cantekinandroidlib.webApi;

import android.os.AsyncTask;

import com.cantekinandroidlib.logger.CustomLogger;

/**
 * post metodunu arka planda çalıştırmak için
 * bunu kullanacak actitivyler ThreadInterface interfacesi ile
 * implemente olmalı, çünkü
 * dönen değer postResult() metodune gönderiliyor.
 * Created by Cantekin on 28.7.2016.
 */

public class ThreadWebApiPost<T> extends AsyncTask<T, String, String> {

    private static final String TAG = "ThreadWebApiPost";
    IThreadDelegete delegate = null;
    T jSonRequest;
    String webApiAddres;

    public ThreadWebApiPost(IThreadDelegete delegate, T jSonRequest, String webApiAddres) {
        this.delegate = delegate;
        this.jSonRequest = jSonRequest;
        this.webApiAddres = webApiAddres;
    }

    @Override
    protected String doInBackground(T... params) {
        String res = new RestApi<T>(webApiAddres).Post(jSonRequest);
        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        if (delegate != null) {
            delegate.postResult(result);
        } else {
            CustomLogger.error(TAG, "delegate is null" + jSonRequest.getClass().getName());
        }
    }
}
