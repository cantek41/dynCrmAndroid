package com.cantekin.androidlibrary.webApi;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.cantekin.logger.CustomLogger;
import com.cantekin.webApi.IThreadDelegete;
import com.cantekin.webApi.ThreadWebApiPost;

import org.junit.Assert;
import org.junit.Before;
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
        new ThreadWebApiPost<UpdateRequestModel>(this, request, webApiAddressGet).execute();
        try {
            async.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postResult(String data) {
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