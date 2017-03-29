package veribis.veribiscrmdyn.Widgets.Items;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import veribis.veribiscrmdyn.Widgets.AbstractWidget;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetTime extends AbstractWidget {
    private String format;
    private Date value;
    private DateFormat dateFormatter;
    private final String defaultFormat = "dd.MM.yyyy HH:mm";

    public WidgetTime(Context context) {
        super(context);
        widget = new TextView(context);
    }

    @Override
    public void init() {
        TextView v = (TextView) widget;
        v.setTextSize(17);
        value = new Date();
        setText();
        v.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR);
                final int mMonth = c.get(Calendar.MONTH);
                final int mDay = c.get(Calendar.DAY_OF_MONTH);
                final int hour = c.get(Calendar.HOUR_OF_DAY);
                final int minute = c.get(Calendar.MINUTE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year,
                                                  final int monthOfYear, final int dayOfMonth) {
                                TimePickerDialog timePickerDialog;
                                timePickerDialog = new TimePickerDialog(getContext(),
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                                Calendar newDate = Calendar.getInstance();
                                                newDate.set(year, monthOfYear, dayOfMonth, selectedHour, selectedMinute);
                                                value = newDate.getTime();
                                                setText();
                                            }
                                        }, hour, minute, true);
                                timePickerDialog.show();
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
