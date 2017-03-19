package veribis.veribiscrmdyn.Fragment.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Filter;
import Model.Form.baseProperties;
import Model.ListRequestModel;
import Model.Sort;
import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.List.ListController;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 28.7.2016.
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends _baseFragment //implements IThreadDelegete, IMyList
{
    private static final String TAG = "ListFragment";
    private ListView dataListView;
    private ListRequestModel request;


    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void initFragment() {
        super.initFragment();
        dataListView = (ListView) getActivity().findViewById(R.id.dataListListView);
        listLoad();
    }

    @Override
    public ListFragment setProp(baseProperties formProperties) {
        this.formProperties = formProperties;
        LayoutId = R.layout.fragment_list;
        request = new ListRequestModel();
        List<String> fields = new ArrayList<>();
        for (Map<String, Object> widget : formProperties.getWidget().getWidgets()) {
            fields.add(String.valueOf(widget.get("field")));
        }
        Sort sort = new Sort();   //= container.getSqlId();
        sort.setDir("asc");
        if (!formProperties.getSortField().isEmpty()) {
            sort.setField(formProperties.getSortField());
        }
        else
            sort.setField("Id");
        request.setSort(sort);
        request.setFields(fields);
        request.setEntity(formProperties.getEntity());
        request.setPageSize(formProperties.getListPageSize());
        request.setSqlId(formProperties.getSqlId());
        if (formProperties.getParentField() != null && formProperties.getParentFieldId() != null) {
            Filter filter = new Filter();
            filter.setField(formProperties.getParentField());
            filter.setOp("eq");
            filter.setVal1(String.valueOf(formProperties.getParentFieldId()));
            request.setFilter(filter);
        }
        return this;
    }

    private void listLoad() {
        ArrayList<Map<String, Object>> dataList = new ArrayList<>();
        ListController listController = new ListController(getActivity(), MyPreference.getPreference(getContext()).getSqlAddress());
        FragmentTransaction frgTra = getFragmentManager().beginTransaction();
        ListAdapter listAdapter = new ListAdapter
                (getContext(), listController, frgTra, R.layout.row_data_list, dataList, formProperties);
        SelectableContainer container = new SelectableContainer();
        container.setDialogTitle(formProperties.getFormTitle());
        container.setTextKey(formProperties.getSearchField());
        View[] searchViews = getSearchView();
        listController
                .setData(container, listAdapter, dataList)
                .searchable((EditText) searchViews[0], (ImageButton) searchViews[1])
                .setRequest(request)
                .run();
        dataListView.setAdapter(listAdapter);
    }

    /**
     * eğer aranabilinir sutun belirtilmemişe arama özelliğini devre dışı bırak
     * eğer üst sayfadan  filitrelenme varsa arama özelliğini devre dışı bırak
     *
     * @return
     */
    private View[] getSearchView() {
        LinearLayout searcLayout = (LinearLayout) getActivity().findViewById(R.id.searchLayout);
        EditText searchText = (EditText) getActivity().findViewById(R.id.searchText);
        ImageButton searchButton = (ImageButton) getActivity().findViewById(R.id.searchButton);
        if (request.getSort() == null || formProperties.getSearchField() == null) {
            searcLayout.setVisibility(View.GONE);
            searchButton = null;
            searchText = null;
        }
        return new View[]{searchText, searchButton};
        //  return searchText;
    }
}
