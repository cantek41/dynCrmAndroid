package com.cantekinandroidlib.androidlibrary.webApi;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Cantekin on 5.2.2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ThreadWebApiPostTest implements IThreadDelegete {
    private CountDownLatch async = null;
    private int REQUEST_READ=10001;

    @Test
    public void testAsync()
    {
        async = new CountDownLatch(1);
        String webApiAddressGet = "http://demo.veribiscrm.com/api/mobile/GetData";
        UpdateRequestModel request = new UpdateRequestModel();
        request.entity = "Activity";
        request.data = new HashMap<String, Object>();
        request.data.put("Id",113);
        CustomLogger.alert("postResult","testAsync");
        new ThreadWebApiPost<UpdateRequestModel>(REQUEST_READ, this, request, webApiAddressGet).execute();
        try {
            async.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postResult(String data,int requestCode) {
        async.countDown();
        CustomLogger.alert("postResult",data);
        Assert.assertNotNull(data);
    }
}
class UpdateRequestModel {
    public String entity;
    public Map<String, Object> data;

    public UpdateRequestModel() {
        data = new HashMap<String, Object>();
    }
}