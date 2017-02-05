package com.cantekinandroidlib.androidlibrary.webApi;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.RestApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cantekin on 5.2.2017.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class RestApiTest {
    @Test
    public void post() throws Exception {
        String webApiAddres = "http://demo.veribiscrm.com/api/mobile/GetData";
        UpdateRequestModel request = new UpdateRequestModel();
        request.entity = "Activity";
        request.data = new HashMap<String, Object>();
        request.data.put("Id", 113);
        String res = new RestApi<UpdateRequestModel>(webApiAddres).Post(request);
        CustomLogger.alert("RestApiTest", res);
        DataModelForm formModel = jsonHelper.stringToObject(res, DataModelForm.class);
        Assert.assertEquals(formModel.Status.ErrCode, 0);
    }

    @Test
    public void postNoId() throws Exception {
        String webApiAddres = "http://demo.veribiscrm.com/api/mobile/GetData";
        UpdateRequestModel request = new UpdateRequestModel();
        request.entity = "Activity";
        request.data = new HashMap<String, Object>();
        request.data.put("DD", 113);
        String res = new RestApi<UpdateRequestModel>(webApiAddres).Post(request);
        CustomLogger.alert("postNoId", res);
        DataModelForm formModel = jsonHelper.stringToObject(res, DataModelForm.class);
        Assert.assertNotNull(formModel);
        Assert.assertNotEquals(formModel.Status.ErrCode, 0);
    }

}

class DataModelForm {
    public Map<String, Object> Data;
    public Status Status;
    public DataModelForm() {
        Data = new HashMap<String, Object>();
    }
}

class Status {
    public int ErrCode;
    public String Message;
}