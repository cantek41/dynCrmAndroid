package veribis.veribiscrmdyn.Widgets.Items;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cantekinandroidlib.logger.CustomLogger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import veribis.veribiscrmdyn.List.ValueController;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.ISelectableWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetDate extends AbstractWidget {
    private String format;
    private Date value;
    private DateFormat dateFormatter;
    private final String defaultFormat = "dd.MM.yyyy";

    public WidgetDate(Context context) {
        super(context);
        widget = new TextView(context);
    }

    @Override
    public void init() {
        TextView v = (TextView) widget;
        ((TextView) widget).setTextSize(17);
        value =new Date();
        setText();
        v.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                value = newDate.getTime();
                                setText();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
    @Override
    public void layoutClick() {

    }
    @Override
    public void setProp(Map<String, Object> properties) {
        super.setProp(properties);
        if (properties.get("format") != null)
            format = String.valueOf(properties.get("format"));
    }

    @Override
    public String getValue() {
        if (value != null) {
            dateFormatter = new SimpleDateFormat(defaultFormat, Locale.ENGLISH);
            return dateFormatter.format(value);
        }
        return null;
    }

    @Override
    public void setValue(String data) {
        dateFormatter = new SimpleDateFormat(defaultFormat, Locale.ENGLISH);
        try {
            value = dateFormatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (value != null) {
            setText();
        }
    }

    private void setText() {
        if (format != null)
            dateFormatter = new SimpleDateFormat(format, Locale.ENGLISH);
        else
            dateFormatter = new SimpleDateFormat(defaultFormat, Locale.ENGLISH);

        ((TextView) widget).setText(dateFormatter.format(value));
    }
}
