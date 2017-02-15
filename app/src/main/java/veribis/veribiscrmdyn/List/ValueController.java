package veribis.veribiscrmdyn.List;


import android.content.Context;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;
import com.google.zxing.common.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.DataModelList;
import Model.Filter;
import Model.ListRequestModel;
import Model.Sort;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 13.2.2017.
 */

public class ValueController implements IThreadDelegete {
    private static final String TAG = "ValueController";
    private static final int REQUEST_READ = 10002;
    private String webApiAddress;
    SelectableContainer container;

    private ListRequestModel request;
    Context context;
    DataModelList resultData;

    public ValueController(Context context) {
        this.context = context;
        webApiAddress = MyPreference.getPreference(context).getListWebApiAddress();
    }

    public ValueController setData(SelectableContainer container) {
        this.container = container;
        return this;
    }

    public void run() {
        if (request == null)
            setRequest();
        getData(1);
    }

    public void setRequest() {
        request = new ListRequestModel();
        request.setSqlId(container.getSqlId());
        Filter filter = new Filter();
        filter.setField(container.getValueKey());
        CustomLogger.alert(TAG, container.getFilterText());
        if (isNumeric(container.getFilterText())) {
            filter.setOp("eq");
            filter.setVal1(Double.valueOf(container.getFilterText()).intValue());
        } else {
            filter.setOp("starts");
            filter.setVal1(container.getFilterText());
        }
        request.setFilter(filter);
        Sort sort = new Sort();   //= container.getSqlId();
        sort.setDir("asc");
        sort.setField(container.getValueKey());
        request.setSort(sort);
        request.setPageSize(1);
        request.setPage(1);
        List<String> fields = new ArrayList<>();
        fields.add(container.getTextKey());
        fields.add(container.getValueKey());
        request.setFields(fields);
    }

    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void getData(int page) {
        if (!((MainActivity) context).isConnection())
            return;
        request.setPage(page);
        CustomLogger.alert(TAG, jsonHelper.objectToJson(request));
        new ThreadWebApiPost<>(REQUEST_READ, this, request, webApiAddress).execute();
    }


    @Override
    public void postResult(String data, int requestCode) {
        // TODO: 25.1.2017 Data model boş yada hatalı gelebilir kontrolünü yap
        CustomLogger.info(TAG, data);
        resultData = jsonHelper.stringToObject(data, DataModelList.class);
        if (resultData.Status.ErrCode == 0 && resultData.Data != null
                && !resultData.Data.isEmpty()) {
            Map<String, Object> val = resultData.Data.get(0);
            container.getWidget().setData(String.valueOf(val.get(container.getTextKey())), String.valueOf(val.get(container.getValueKey())));
        }
    }


}
