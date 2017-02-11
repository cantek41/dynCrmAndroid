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

public class ThreadWebApiPostFile extends AsyncTask<String, String, String> {

    private static final String TAG = "ThreadWebApiPostFile";
    private IThreadDelegete delegate = null;
    private final byte[] byteArray;
    private final String filename;
    private final String webApiAddres;
    private final int requestCode;

    public ThreadWebApiPostFile(int requestCode,IThreadDelegete delegate,byte[] byteArray, String filename, String webApiAddres) {
        this.delegate = delegate;
        this.byteArray = byteArray;
        this.filename = filename;
        this.webApiAddres = webApiAddres;
        this.requestCode = requestCode;
    }

    @Override
    protected String doInBackground(String... params) {
        return new RestApi(webApiAddres).PostFile(byteArray, filename);
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
