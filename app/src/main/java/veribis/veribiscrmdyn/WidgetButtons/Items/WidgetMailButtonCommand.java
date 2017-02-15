package veribis.veribiscrmdyn.WidgetButtons.Items;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.WidgetButtons.IWidgetButton;
import veribis.veribiscrmdyn.WidgetButtons.IWidgetButtonCommand;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class WidgetMailButtonCommand extends IWidgetButton implements IWidgetButtonCommand {
    View parent;

    public WidgetMailButtonCommand(Context context) {
        super(context);
    }


    @Override
    public String name() {
        return "Kaydet";
    }

    @Override
    public int icon() {
        return R.mipmap.widget_mail;
    }

    @Override
    public void execute() {
        Intent intent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", getParentValue(), null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "VeribisCRM");
        getContext().startActivity(intent);
    }

    @Override
    public void setParent(View parent) {
        this.parent = parent;
    }

    private String getParentValue() {
        String result = null;
        if (parent instanceof TextView) {
            result = ((TextView) parent).getText().toString();
        } else if (parent instanceof EditText) {
            result = ((EditText) parent).getText().toString();
        }
        return result;
    }

}
