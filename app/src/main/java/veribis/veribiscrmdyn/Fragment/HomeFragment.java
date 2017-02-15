package veribis.veribiscrmdyn.Fragment;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import Model.Form.baseProperties;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import veribis.veribiscrmdyn.R;

public class HomeFragment extends _baseFragment {
    private PieChartView chart;
    private PieChartData data;
    private ColumnChartView chartC;
    private ColumnChartData dataC;

    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = false;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public HomeFragment setProp(baseProperties prop) {
        this.LayoutId = R.layout.fragment_home;
        formProperties = new baseProperties();
        formProperties.setFormTitle("DashBoard");
        formProperties.setActionButtonIsVisible(false);
        return this;
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        chart = (PieChartView) getActivity().findViewById(R.id.chart);
        chartC = (ColumnChartView) getActivity().findViewById(R.id.chart3);
        // chart.setOnValueTouchListener(new ValueTouchListener());
        chart.setCircleFillRatio(1.0f);
        hasLabels = true;
        hasLabelsOutside = false;
        hasCenterCircle = true;
        hasCenterText1 = true;
        hasCenterText2 = true;
        isExploded = false;
        hasLabelForSelected = false;

        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < 6; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(sliceValue);

        }

        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        if (hasCenterText1) {
            data.setCenterText1("Aktivite!");
            data.setCenterText1FontSize(10);
            data.setCenterText1Color(Color.WHITE);
        }

        chart.setPieChartData(data);
        generateDefaultData();
    }
    private void generateDefaultData() {
        int numSubcolumns = 1;
        int numColumns = 8;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
          //  column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        dataC = new ColumnChartData(columns);
        Axis axisX = new Axis().setHasLines(true);
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Çalışanlar");
        axisY.setName("Satışlar");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        chartC.setColumnChartData(dataC);

    }

}
